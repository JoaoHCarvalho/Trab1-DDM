package com.example.trab1_ddm.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.trab1_ddm.R
import com.example.trab1_ddm.ViewModel.JogosAdapter
import com.example.trab1_ddm.ViewModel.JogosConcluidosAdapter
import com.example.trab1_ddm.ViewModel.UserViewModel
import com.example.trab1_ddm.dao.UserDAO
import com.example.trab1_ddm.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var userViewModel: UserViewModel

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val usuarioDao = UserDAO(requireContext())
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        if(usuarioDao.getAllUsuarios().isEmpty()){
            userViewModel.clearJogosMaisJogados()
            userViewModel.clearJogosConcluidos()
        }
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val listView: ListView = binding.root.findViewById(R.id.JogosMaisJogados_ListView)
        userViewModel.jogosMaisJogados.observe(viewLifecycleOwner) { jogosMaisJogados ->
            val adapter = JogosAdapter(requireContext(), jogosMaisJogados)
            listView.adapter = adapter
        }
        usuarioDao.getSteamIdById(1)?.let { userViewModel.getJogosTempo(it) }

         val listViewConcluidos: ListView = binding.root.findViewById(R.id.JogosConcluídos_ListView)
        userViewModel.jogosConcluidos.observe(viewLifecycleOwner) { jogosConcluidos ->
            //Configurar o adapter com os jogos concluídos recebidos
            val adapter = JogosConcluidosAdapter(requireContext(), jogosConcluidos)
            listViewConcluidos.adapter = adapter
        }
        usuarioDao.getSteamIdById(1)?.let { userViewModel.getJogosConcluidos(it) }
        println(usuarioDao.getAllUsuarios())

        homeViewModel.text.observe(viewLifecycleOwner) {

        }

        if(usuarioDao.getAllUsuarios().isEmpty()){
            userViewModel.clearJogosMaisJogados()
            userViewModel.clearJogosConcluidos()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}