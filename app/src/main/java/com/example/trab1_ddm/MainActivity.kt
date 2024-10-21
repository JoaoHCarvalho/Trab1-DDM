package com.example.trab1_ddm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.trab1_ddm.ViewModel.JogosAdapter
import com.example.trab1_ddm.ViewModel.JogosConcluidosAdapter
import com.example.trab1_ddm.ViewModel.UserViewModel
import com.example.trab1_ddm.ViewModel.ViewModelFactory
import com.example.trab1_ddm.databinding.ActivityMainBinding
import com.example.trab1_ddm.model.Jogo
import com.example.trab1_ddm.retrofit.RetrofitInitializer
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_home)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_library
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        userViewModel = ViewModelProvider(this, ViewModelFactory())
            .get(UserViewModel::class.java)

        //Botão de teste na fragment home
        val btnmeuso: Button = findViewById(R.id.button3)
        btnmeuso.setOnClickListener {
            //userViewModel.createUser("erf","erf","erf")
            //userViewModel.assoSteamID(1,"76561198973296498")
            //userViewModel.getJogos("76561198973296498")
            //userViewModel.selectConq("76561198973296498")
            //userViewModel.setConq("76561198973296498")
            //userViewModel.setTrofeu("76561198973296498")
            //userViewModel.associarAll()
            //userViewModel.getJogosConcluidos("76561198973296498")

        }

        val listView: ListView = findViewById(R.id.JogosMaisJogados_ListView)

        userViewModel.jogosMaisJogados.observe(this) { jogosMaisJogados ->
            val adapter = JogosAdapter(this, jogosMaisJogados)
            listView.adapter = adapter
        }

        userViewModel.getJogosTempo("76561198973296498")

       // val listConqAd: TextView = findViewById(R.id.txt_adquiridas)
        //val listConqDi: TextView = findViewById(R.id.txt_disponivel)

        //userViewModel.selectConq()

        // Referência ao ListView dos Jogos Concluídos
        val listViewConcluidos: ListView = findViewById(R.id.JogosConcluídos_ListView)

        userViewModel.jogosConcluidos.observe(this) { jogosConcluidos ->
            // Configurar o adapter com os jogos concluídos recebidos
            val adapter = JogosConcluidosAdapter(this, jogosConcluidos)
            listViewConcluidos.adapter = adapter
        }

        // Chamar o método para buscar os jogos concluídos
        if (userViewModel.jogosConcluidos.value == null) {
            userViewModel.getJogosConcluidos("76561198973296498")
        }
        onBackPressedDispatcher.addCallback(this) {
            userViewModel.getJogosConcluidos("76561198973296498")
        }
        val intent = Intent(this, LibraryActivity::class.java)
        startActivity(intent)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onStart() {
        super.onStart()
        val listViewConcluidos: ListView = findViewById(R.id.JogosConcluídos_ListView)
        userViewModel.jogosConcluidos.observe(this) { jogosConcluidos ->
            // Configurar o adapter com os jogos concluídos recebidos
            val adapter = JogosConcluidosAdapter(this, jogosConcluidos)
            listViewConcluidos.adapter = adapter
        }
    }

    override fun onResume() {
        super.onResume()
        val listViewConcluidos: ListView = findViewById(R.id.JogosConcluídos_ListView)
        userViewModel.jogosConcluidos.observe(this) { jogosConcluidos ->
            // Configurar o adapter com os jogos concluídos recebidos
            val adapter = JogosConcluidosAdapter(this, jogosConcluidos)
            listViewConcluidos.adapter = adapter
        }
    }

    override fun onPause() {
        super.onPause()
        val listViewConcluidos: ListView = findViewById(R.id.JogosConcluídos_ListView)
        userViewModel.jogosConcluidos.observe(this) { jogosConcluidos ->
            // Configurar o adapter com os jogos concluídos recebidos
            val adapter = JogosConcluidosAdapter(this, jogosConcluidos)
            listViewConcluidos.adapter = adapter
        }

    }

    override fun onRestart() {
        super.onRestart()
        val listViewConcluidos: ListView = findViewById(R.id.JogosConcluídos_ListView)
        userViewModel.jogosConcluidos.observe(this) { jogosConcluidos ->
            // Configurar o adapter com os jogos concluídos recebidos
            val adapter = JogosConcluidosAdapter(this, jogosConcluidos)
            listViewConcluidos.adapter = adapter
        }
    }


}