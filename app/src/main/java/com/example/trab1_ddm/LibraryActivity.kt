package com.example.trab1_ddm

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.trab1_ddm.ViewModel.JogosAdapter
import com.example.trab1_ddm.ViewModel.UserViewModel

class LibraryActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_library) // Certifique-se de que está usando o layout correto.

        // Inicialize o ViewModel
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // Referência ao ListView
        val listView: ListView = findViewById(R.id.listView)

        // Observar as mudanças nos jogos mais jogados
        userViewModel.allJogos.observe(this) { jogosMaisJogados ->
            // Configurar o adapter com os jogos mais jogados recebidos
            val adapter = JogosAdapter(this, jogosMaisJogados)
            listView.adapter = adapter
        }

        // Chamar a função para buscar os jogos do usuário
        userViewModel.getAllJogos("76561198973296498")
    }
}