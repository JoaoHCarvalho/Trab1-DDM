package com.example.trab1_ddm.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.trab1_ddm.R
import com.example.trab1_ddm.ViewModel.UserViewModel
import com.example.trab1_ddm.dao.UserDAO
import com.example.trab1_ddm.dao.UsuarioDAO
import com.example.trab1_ddm.databinding.ActivityTelaDeCadastroBinding
import com.example.trab1_ddm.databinding.FragmentTelaConfiguracoesBinding
import com.example.trab1_ddm.model.Usuario
import com.example.trab1_ddm.ui.home.HomeFragment
import com.google.android.material.textfield.TextInputEditText

class RegisterFragment : Fragment() {
    private var _binding: ActivityTelaDeCadastroBinding? = null
    private lateinit var userViewModel: UserViewModel
    private lateinit var nameInput: TextInputEditText
    private lateinit var senhaInput: TextInputEditText
    private lateinit var apelidoInput: TextInputEditText
    private lateinit var buttonCadastrar: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_tela_de_cadastro, container, false)
        _binding = ActivityTelaDeCadastroBinding.inflate(inflater, container, false)


        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        nameInput = view.findViewById(R.id.nameInput)
        senhaInput = view.findViewById(R.id.senhaInput)
        apelidoInput = view.findViewById(R.id.apelidoInput)
        buttonCadastrar = view.findViewById(R.id.buttonCadastrar)


        buttonCadastrar.setOnClickListener {
            val nome = nameInput.text.toString()
            val senha = senhaInput.text.toString()
            val apelido = apelidoInput.text.toString()

            if (nome.isNotBlank() && senha.isNotBlank() && apelido.isNotBlank()) {
                // Criação do usuário no ViewModel
                userViewModel.createUser(nome, senha, apelido)

                // Adição ao banco de dados SQLite
                val usuario = Usuario(
                    nomeUsuario = nome,
                    senha = senha,
                    apelido = apelido
                )

                val usuarioDao = UserDAO(requireContext())
                val resultado = usuarioDao.addUsuario(usuario)
                if (resultado == -1L) {
                    Toast.makeText(requireContext(), "Já existe um usuário cadastrado", Toast.LENGTH_SHORT).show()
                } else {
                    // Navega para a tela inicial
                    val navController = findNavController()
                    navController.navigate(R.id.nav_home)
                }
            } else {
                Toast.makeText(requireContext(), "Todos os campos são obrigatórios", Toast.LENGTH_SHORT).show()
            }
        }

        return view

    }


}