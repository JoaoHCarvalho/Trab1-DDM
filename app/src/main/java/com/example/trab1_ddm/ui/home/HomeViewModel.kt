package com.example.trab1_ddm.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trab1_ddm.model.Jogo
import com.example.trab1_ddm.retrofit.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _jogosMaisJogados = MutableLiveData<List<Jogo>>()
    val jogosMaisJogados: LiveData<List<Jogo>> get() = _jogosMaisJogados
    fun clearJogosMaisJogados() {
        _jogosMaisJogados.value = emptyList()
    }


    private val _jogosConcluidos = MutableLiveData<List<Jogo>>()
    val jogosConcluidos: LiveData<List<Jogo>> get() = _jogosConcluidos
    fun clearJogosConcluidos() {
        _jogosConcluidos.value = emptyList()
    }

    fun getJogosConcluidos(s: String) {
        val call = RetrofitInitializer().getUsuario().getGames(s)
        call?.enqueue(object : Callback<List<Jogo>?> {
            override fun onResponse(call: Call<List<Jogo>?>, response: Response<List<Jogo>?>) {
                if (response.isSuccessful) {
                    response.body()?.let { jogosList ->
                        val jogosCompletos = jogosList.filter { jogo ->
                            jogo.n_conquistas == jogo.f_conquistas && jogo.n_conquistas > 0
                        }.take(5)

                        _jogosConcluidos.value = jogosCompletos
                        Log.i("Retrofit", jogosCompletos.toString())
                    }
                } else {
                    Log.i("Retrofit", "Erro: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Jogo>?>, t: Throwable) {
                Log.i("Retrofit", "Falha na requisição: ${t.message}")
            }
        })
    }
    fun getJogosTempo(s: String) {
        val call = RetrofitInitializer().getUsuario().getGames(s)
        call?.enqueue(object : Callback<List<Jogo>?> {
            override fun onResponse(call: Call<List<Jogo>?>, response: Response<List<Jogo>?>) {
                if (response.isSuccessful) {
                    response.body()?.let { jogosList ->
                        val jogosComHoras = jogosList.map { jogo ->
                            jogo.copy(tempoDeJogo = jogo.tempoDeJogo / 60)
                        }
                        val topJogos = jogosComHoras.sortedByDescending { it.tempoDeJogo }.take(5)
                        _jogosMaisJogados.value = topJogos
                        Log.i("Retrofit", topJogos.toString())
                    }
                } else {
                    Log.i("Retrofit", "Erro: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Jogo>?>, t: Throwable) {
                Log.i("Retrofit", "Falha na requisição: ${t.message}")
            }
        })
    }
}