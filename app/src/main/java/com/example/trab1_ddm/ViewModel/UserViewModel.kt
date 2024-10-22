package com.example.trab1_ddm.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trab1_ddm.model.Conquista
import com.example.trab1_ddm.model.Jogo
import com.example.trab1_ddm.retrofit.RetrofitInitializer
import com.example.trab1_ddm.service.UserRequest
import com.example.trab1_ddm.service.UsuarioService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel: ViewModel(){

    fun getJogos(s: String) {

        val call = RetrofitInitializer().getApi().selectJogos(s)
        call?.enqueue(object : Callback<Any?> {
            override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
                if (response.isSuccessful()) {
                    val cep: Any? = response.body()
                    Log.i("Retrofit", cep.toString())
                } else {
                    Log.i("Retrofit", response.code().toString() + "")
                }
            }

            override fun onFailure(call: Call<Any?>, t: Throwable) {
                Log.i("Retrofit", "falha")
            }
        })
    }

    fun delete(){
        val call = RetrofitInitializer().getApi().deleteEmpty()
        call?.enqueue(object : Callback<Any?>{
            override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
                if (response.isSuccessful()){
                    val temp: Any? = response.body()
                    Log.i("Retrofit", temp.toString())
                } else {
                    Log.i("Retrofit", response.code().toString()+"")
                }
            }

            override fun onFailure(call: Call<Any?>, t: Throwable) {
                Log.i("Retrofit", "falha")
            }
        })
    }

    fun selectConq(s:String){
        val call = RetrofitInitializer().getApi().selectConquista(s)
        call?.enqueue(object : Callback<String?>{
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                if (response.isSuccessful()){
                    val temp: Any? = response.body()
                    Log.i("Retrofit", temp.toString())
                } else {
                    Log.i("Retrofit", response.code().toString()+"")
                }
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                Log.i("Retrofit", "falha")
            }
        })
    }

    fun setConq(s:String){
        val call = RetrofitInitializer().getApi().associarJogoConq(s)
        call?.enqueue(object : Callback<Any?>{
            override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
                if (response.isSuccessful()){
                    val temp: Any? = response.body()
                    Log.i("Retrofit", temp.toString())
                } else {
                    Log.i("Retrofit", response.code().toString()+"")
                }
            }

            override fun onFailure(call: Call<Any?>, t: Throwable) {
                Log.i("Retrofit", "falha")
            }
        })
    }

    fun setTrofeu(s:String){
        val call = RetrofitInitializer().getApi().setTrofeus(s)
        call?.enqueue(object : Callback<Any?>{
            override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
                if (response.isSuccessful()){
                    val temp: Any? = response.body()
                    Log.i("Retrofit", temp.toString())
                } else {
                    Log.i("Retrofit", response.code().toString()+"")
                }
            }

            override fun onFailure(call: Call<Any?>, t: Throwable) {
                Log.i("Retrofit", "falha")
            }
        })
    }

    fun createUser(nomeusuario: String, apelido: String, senha: String) {

        val call = RetrofitInitializer().getUsuario().createUser(UserRequest(nomeusuario, apelido, senha))
        call?.enqueue(object : Callback<Any?>{
            override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
                if (response.isSuccessful()){
                    val temp: Any? = response.body()
                    Log.i("Retrofit", temp.toString())
                } else {
                    Log.i("Retrofit", response.code().toString()+"")
                }
            }

            override fun onFailure(call: Call<Any?>, t: Throwable) {
                Log.i("Retrofit", "falha")
            }
        })
    }

    fun assoSteamID(id: Int,s: String){
        val call = RetrofitInitializer().getUsuario().assocSteam(id,s)
        call?.enqueue(object : Callback<Void?>{
            override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                if (response.isSuccessful()){
                    val temp: Any? = response.body()
                    Log.i("Retrofit", temp.toString())
                } else {
                    Log.i("Retrofit", response.code().toString()+"")
                }
            }

            override fun onFailure(call: Call<Void?>, t: Throwable) {
                Log.i("Retrofit", "falha")
            }
        })
    }

    fun getImagem(s: String) {
        val call = RetrofitInitializer().getUsuario().select(s)
        call?.enqueue(object : Callback<Any?>{
            override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
                if (response.isSuccessful()){
                    val temp: Any? = response.body()
                    Log.i("Retrofit", temp.toString())
                } else {
                    Log.i("Retrofit", response.code().toString()+"")
                }
            }

            override fun onFailure(call: Call<Any?>, t: Throwable) {
                Log.i("Retrofit", "falha")
            }
        })
    }

    fun associarAll() {
        val call = RetrofitInitializer().getUsuario().associate()
        call?.enqueue(object : Callback<Void?>{
            override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                if (response.isSuccessful()){
                    val temp: Any? = response.body()
                    Log.i("Retrofit", temp.toString())
                } else {
                    Log.i("Retrofit", response.code().toString()+"")
                }
            }

            override fun onFailure(call: Call<Void?>, t: Throwable) {
                Log.i("Retrofit", "falha")
            }
        })
    }

    private val _jogos = MutableLiveData<List<Jogo>>()
    val jogos: LiveData<List<Jogo>> get() = _jogos


    fun getJogosTempo(s: String) {
        val call = RetrofitInitializer().getUsuario().getGames(s)
        call?.enqueue(object : Callback<List<Jogo>?> {
            override fun onResponse(call: Call<List<Jogo>?>, response: Response<List<Jogo>?>) {
                if (response.isSuccessful) {
                    response.body()?.let { jogosList ->
                        // Converter o tempo de jogo de minutos para horas antes de atualizar a lista
                        val jogosComHoras = jogosList.map { jogo ->
                            jogo.copy(tempoDeJogo = jogo.tempoDeJogo / 60)
                        }

                        // Ordenar os jogos por tempo de jogo em horas e pegar os 5 primeiros
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


    private val _jogosMaisJogados = MutableLiveData<List<Jogo>>()
    val jogosMaisJogados: LiveData<List<Jogo>> get() = _jogosMaisJogados

    private val _jogosConcluidos = MutableLiveData<List<Jogo>>()
    val jogosConcluidos: LiveData<List<Jogo>> get() = _jogosConcluidos

    fun getJogosConcluidos(s: String) {
        val call = RetrofitInitializer().getUsuario().getGames(s)
        call?.enqueue(object : Callback<List<Jogo>?> {
            override fun onResponse(call: Call<List<Jogo>?>, response: Response<List<Jogo>?>) {
                if (response.isSuccessful) {
                    response.body()?.let { jogosList ->
                        // Filtrar jogos onde n_conquistas é igual a f_conquistas
                        val jogosCompletos = jogosList.filter { jogo ->
                            jogo.n_conquistas == jogo.f_conquistas && jogo.n_conquistas > 0
                        }.take(5)  // Pegar apenas os 5 primeiros, se existirem

                        // Atualizar o LiveData com os jogos filtrados
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

    private val allJogos_ = MutableLiveData<List<Jogo>>()
    val allJogos: LiveData<List<Jogo>> get() = allJogos_

    fun getAllJogos(s: String) {
        val call = RetrofitInitializer().getUsuario().getGames(s)
        call?.enqueue(object : Callback<List<Jogo>?> {
            override fun onResponse(call: Call<List<Jogo>?>, response: Response<List<Jogo>?>) {
                if (response.isSuccessful) {
                    response.body()?.let { jogosList ->
                        // Ordenar os jogos por nome em ordem alfabética
                        val jogosOrdenados = jogosList.sortedBy { it.nome }

                        // Atualizar o LiveData com os jogos ordenados
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

