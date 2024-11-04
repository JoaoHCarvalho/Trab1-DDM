package com.example.trab1_ddm.ui.library

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trab1_ddm.model.Jogo
import com.example.trab1_ddm.retrofit.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LibraryViewModel : ViewModel() {

    private val allJogos_ = MutableLiveData<List<Jogo>>()
    val allJogos: LiveData<List<Jogo>> get() = allJogos_

    fun clearAllJogos(){
        allJogos_.value = emptyList()
    }

    fun getAllJogos(s: String) {
        val call = RetrofitInitializer().getUsuario().getGames(s)
        call?.enqueue(object : Callback<List<Jogo>?> {
            override fun onResponse(call: Call<List<Jogo>?>, response: Response<List<Jogo>?>) {
                if (response.isSuccessful) {
                    response.body()?.let { jogosList ->
                        val jogosComHoras = jogosList.map { jogo ->
                            jogo.copy(tempoDeJogo = jogo.tempoDeJogo / 60)
                        }
                        val jogosOrdenados = jogosComHoras.sortedBy { it.nome }
                        allJogos_.value = jogosOrdenados
                        Log.i("Retrofit", jogosOrdenados.toString())
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