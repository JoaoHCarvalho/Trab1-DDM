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

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val listView: ListView = findViewById(R.id.listView)

        userViewModel.allJogos.observe(this) { jogosMaisJogados ->
            // Configurar o adapter com os jogos mais jogados recebidos
            val adapter = JogosAdapter(this, jogosMaisJogados)
            listView.adapter = adapter
        }

        userViewModel.getAllJogos("76561198973296498")
    }
}