package com.example.trab1_ddm.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.trab1_ddm.R
import com.example.trab1_ddm.Adapter.JogosAdapter
import com.example.trab1_ddm.Adapter.JogosConcluidosAdapter
import com.example.trab1_ddm.dao.UserDAO
import com.example.trab1_ddm.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var homeViewModel: HomeViewModel

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
        if(usuarioDao.getAllUsuarios().isEmpty()){
            homeViewModel.clearJogosMaisJogados()
            homeViewModel.clearJogosConcluidos()
        }
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val listView: ListView = binding.root.findViewById(R.id.JogosMaisJogados_ListView)
        homeViewModel.jogosMaisJogados.observe(viewLifecycleOwner) { jogosMaisJogados ->
            val adapter = JogosAdapter(requireContext(), jogosMaisJogados)
            listView.adapter = adapter
        }
        usuarioDao.getSteamIdById(1)?.let { homeViewModel.getJogosTempo(it) }

         val listViewConcluidos: ListView = binding.root.findViewById(R.id.JogosConcluídos_ListView)
        homeViewModel.jogosConcluidos.observe(viewLifecycleOwner) { jogosConcluidos ->
            val adapter = JogosConcluidosAdapter(requireContext(), jogosConcluidos)
            listViewConcluidos.adapter = adapter
        }
        usuarioDao.getSteamIdById(1)?.let { homeViewModel.getJogosConcluidos(it) }
        println(usuarioDao.getAllUsuarios())


        if(usuarioDao.getAllUsuarios().isEmpty()){
            homeViewModel.clearJogosMaisJogados()
            homeViewModel.clearJogosConcluidos()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}