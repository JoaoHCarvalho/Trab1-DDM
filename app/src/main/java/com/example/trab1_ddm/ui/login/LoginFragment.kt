package com.example.trab1_ddm.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.trab1_ddm.R
import com.example.trab1_ddm.dao.UserDAO
import com.example.trab1_ddm.databinding.ActivityTelaDeLoginBinding
import com.example.trab1_ddm.model.Usuario
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

        private var _binding: ActivityTelaDeLoginBinding? = null
        private val loginViewModel: LoginViewModel by viewModels()

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.activity_tela_de_login, container, false)
            _binding = ActivityTelaDeLoginBinding.inflate(inflater, container, false)

            val buttonLogin = view.findViewById<Button>(R.id.buttonLogin)
            val buttonLevaCadastrar = view.findViewById<Button>(R.id.buttonLevaCadastrar)
            val nomeInput = view.findViewById<TextInputEditText>(R.id.nameInput)
            val senhaInput = view.findViewById<TextInputEditText>(R.id.confirmaSenhaInput)

            buttonLogin.setOnClickListener {
                val nome = nomeInput.text.toString()
                val senha = senhaInput.text.toString()
                if (nome.isNotBlank() && senha.isNotBlank()) {
                    loginViewModel.getUserByName(nome) { usuario ->
                        if (usuario != null) {
                            if (usuario.senha == senha) {
                                salvarUsuarioNoSQLite(usuario)
                                val navController = findNavController()
                                navController.navigate(R.id.nav_home)
                            } else {
                                Toast.makeText(requireContext(), "Senha incorreta", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(requireContext(), "Usuário não encontrado", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(requireContext(), "Todos os campos são obrigatórios", Toast.LENGTH_SHORT).show()
                }
            }

            buttonLevaCadastrar.setOnClickListener {
                val navController = findNavController()
                navController.navigate(R.id.cadastro)
            }

            return view
        }

        private fun salvarUsuarioNoSQLite(usuario: Usuario) {
            val usuarioDao = UserDAO(requireContext())
            lifecycleScope.launch {
                usuarioDao.addUsuario(usuario)
                Toast.makeText(requireContext(), "Usuário salvo com sucesso", Toast.LENGTH_SHORT).show()
            }
            println(usuarioDao.getAllUsuarios())
        }
    }