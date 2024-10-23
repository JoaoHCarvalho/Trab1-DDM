import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.trab1_ddm.R
import com.example.trab1_ddm.ViewModel.JogosAdapter
import com.example.trab1_ddm.ViewModel.UserViewModel
import com.example.trab1_ddm.databinding.FragmentLibraryBinding
import kotlin.math.log

class LibraryFragment : Fragment() {

    private var _binding: FragmentLibraryBinding? = null
    private lateinit var userViewModel: UserViewModel
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val listView: ListView = binding.root.findViewById(R.id.listView)

        userViewModel.allJogos.observe(viewLifecycleOwner) { jogosMaisJogados ->
            val adapter = JogosAdapter(requireContext(), jogosMaisJogados)
            listView.adapter = adapter

            // Detectar o clique no item da lista
            listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                val jogoSelecionado = jogosMaisJogados[position]

                // Criar um bundle com os dados do jogo selecionado
                val bundle = Bundle().apply {
                    putString("NomedoJogo", jogoSelecionado.nome)
                    putString("Conquistadas:", jogoSelecionado.f_conquistas.toString())
                    putString("Total:", jogoSelecionado.n_conquistas.toString())
                    // Adicione outros dados conforme necessário
                    println(jogoSelecionado.nome)
                    println(jogoSelecionado.f_conquistas)
                    println(jogoSelecionado.n_conquistas)
                }


                // Navegar para o fragmento de detalhes com o bundle
                findNavController().navigate(R.id.action_libraryFragment_to_gameDetailsFragment, bundle)
            }
        }

        userViewModel.getAllJogos("76561198973296498")

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
