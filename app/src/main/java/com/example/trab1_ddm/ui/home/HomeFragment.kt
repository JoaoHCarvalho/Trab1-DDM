package com.example.trab1_ddm.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.trab1_ddm.R
import com.example.trab1_ddm.ViewModel.JogosAdapter
import com.example.trab1_ddm.ViewModel.JogosConcluidosAdapter
import com.example.trab1_ddm.ViewModel.UserViewModel
import com.example.trab1_ddm.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var userViewModel: UserViewModel
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val listView: ListView = binding.root.findViewById(R.id.JogosMaisJogados_ListView)
        userViewModel.jogosMaisJogados.observe(viewLifecycleOwner) { jogosMaisJogados ->
            val adapter = JogosAdapter(requireContext(), jogosMaisJogados)
            listView.adapter = adapter
        }
        userViewModel.getJogosTempo("76561198973296498")



        val listViewConcluidos: ListView = binding.root.findViewById(R.id.JogosConcluídos_ListView)
        userViewModel.jogosConcluidos.observe(viewLifecycleOwner) { jogosConcluidos ->
            // Configurar o adapter com os jogos concluídos recebidos
            val adapter = JogosConcluidosAdapter(requireContext(), jogosConcluidos)
            listViewConcluidos.adapter = adapter
        }
        userViewModel.getJogosConcluidos("76561198973296498")


        // val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
          //  textView.text = it
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}