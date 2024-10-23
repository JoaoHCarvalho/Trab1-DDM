package com.example.trab1_ddm.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.trab1_ddm.R
import com.example.trab1_ddm.ViewModel.UserViewModel
import com.example.trab1_ddm.ui.home.HomeFragment
import com.google.android.material.textfield.TextInputEditText

class RegisterFragment : Fragment() {

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

        // Inicializa o ViewModel
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // Liga os campos aos componentes da interface
        nameInput = view.findViewById(R.id.nameInput)
        senhaInput = view.findViewById(R.id.senhaInput)
        apelidoInput = view.findViewById(R.id.apelidoInput)
        buttonCadastrar = view.findViewById(R.id.buttonCadastrar)

        // Define o listener para o botão de cadastro
        buttonCadastrar.setOnClickListener {
            val nome = nameInput.text.toString()
            val senha = senhaInput.text.toString()
            val apelido = apelidoInput.text.toString()

            // Verifica se os campos não estão vazios
            if (nome.isNotBlank() && senha.isNotBlank() && apelido.isNotBlank()) {
                // Chama o método no ViewModel para realizar o cadastro
                userViewModel.createUser(nome, senha, apelido)
                goToHomeFragment()
            } else {
                Toast.makeText(requireContext(), "Todos os campos são obrigatórios", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
    private fun goToHomeFragment() {
        // Navega para o fragment de Home
        val fragmentManager = parentFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, HomeFragment())  // Substitua pelo ID correto do container de fragmentos
            .addToBackStack(null)  // Adiciona à pilha para poder voltar
            .commit()
    }
}