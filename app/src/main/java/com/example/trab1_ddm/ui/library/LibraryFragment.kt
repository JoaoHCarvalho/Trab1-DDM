package com.example.trab1_ddm.ui.library

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.trab1_ddm.R
import com.example.trab1_ddm.ViewModel.JogosAdapter
import com.example.trab1_ddm.ViewModel.UserViewModel
import com.example.trab1_ddm.databinding.FragmentLibraryBinding

class LibraryFragment : Fragment() {

    private var _binding: FragmentLibraryBinding? = null
    private lateinit var userViewModel: UserViewModel
     //This property is only valid between onCreateView and
     //onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(LibraryViewModel::class.java)

        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // Referência ao ListView
        val listView: ListView = binding.root.findViewById(R.id.listView)

        // Observar as mudanças nos jogos mais jogados
        userViewModel.allJogos.observe(viewLifecycleOwner) { jogosMaisJogados ->
            // Configurar o adapter com os jogos mais jogados recebidos
            val adapter = JogosAdapter(requireContext(), jogosMaisJogados)
            listView.adapter = adapter
        }

        // Chamar a função para buscar os jogos do usuário
        userViewModel.getAllJogos("76561198973296498")




        //val textView: TextView = binding.textSlideshow
        slideshowViewModel.text.observe(viewLifecycleOwner) {
            //textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}