package com.example.trab1_ddm.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.trab1_ddm.R
import com.example.trab1_ddm.databinding.ActivityTelaDeLoginBinding

class LoginFragment : Fragment() {

    private var _binding: ActivityTelaDeLoginBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_tela_de_login, container, false)
        _binding = ActivityTelaDeLoginBinding.inflate(inflater, container, false)


        val buttonLogin = view.findViewById<Button>(R.id.buttonLogin)
        val buttonLevaCadastrar = view.findViewById<Button>(R.id.buttonLevaCadastrar)

        buttonLogin.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.nav_home)  }
        buttonLevaCadastrar.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.cadastro)
        }

        return view
    }
    }