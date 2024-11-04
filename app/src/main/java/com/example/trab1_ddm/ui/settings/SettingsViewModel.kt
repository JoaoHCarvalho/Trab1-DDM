package com.example.trab1_ddm.ui.settings

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trab1_ddm.model.Usuario
import com.example.trab1_ddm.retrofit.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingsViewModel  : ViewModel() {

    fun changeSenha(user: String, senha: String, callback: (Usuario?) -> Unit) {
        val call = RetrofitInitializer().getUsuario().setNewSenha(user, senha)
        call?.enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if (response.isSuccessful) {
                    val usuario = response.body()
                    callback(usuario)
                } else {
                    Log.i("Retrofit", response.code().toString())
                    callback(null)
                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                Log.i("Retrofit", "falha")
                callback(null)
            }
        })
    }

    fun changeApelido(user: String, apelido: String, callback: (Usuario?) -> Unit) {
        val call = RetrofitInitializer().getUsuario().setNewApelido(user, apelido)
        call?.enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if (response.isSuccessful) {
                    val usuario = response.body()
                    callback(usuario)
                } else {
                    Log.i("Retrofit", response.code().toString())
                    callback(null)
                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                Log.i("Retrofit", "falha")
                callback(null)
            }
        })
    }
    fun setSteamId(user: String, steamId: String, callback: (Usuario?) -> Unit) {
        val call = RetrofitInitializer().getUsuario().setSteamByName(user, steamId)
        call?.enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if (response.isSuccessful) {
                    val usuario = response.body()
                    callback(usuario)
                } else {
                    Log.i("Retrofit", response.code().toString())
                    callback(null)
                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                Log.i("Retrofit", "falha")
                callback(null)
            }
        })
    }fun getJogos(s: String) {

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

    fun selectConq(s: String) {
        val call = RetrofitInitializer().getApi().selectConquista(s)
        call?.enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                if (response.isSuccessful()) {
                    val temp: Any? = response.body()
                    Log.i("Retrofit", temp.toString())
                } else {
                    Log.i("Retrofit", response.code().toString() + "")
                }
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                Log.i("Retrofit", "falha")
            }
        })
    }

    fun setConq(s: String) {
        val call = RetrofitInitializer().getApi().associarJogoConq(s)
        call?.enqueue(object : Callback<Any?> {
            override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
                if (response.isSuccessful()) {
                    val temp: Any? = response.body()
                    Log.i("Retrofit", temp.toString())
                } else {
                    Log.i("Retrofit", response.code().toString() + "")
                }
            }

            override fun onFailure(call: Call<Any?>, t: Throwable) {
                Log.i("Retrofit", "falha")
            }
        })
    }

    fun setTrofeu(s: String) {
        val call = RetrofitInitializer().getApi().setTrofeus(s)
        call?.enqueue(object : Callback<Any?> {
            override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
                if (response.isSuccessful()) {
                    val temp: Any? = response.body()
                    Log.i("Retrofit", temp.toString())
                } else {
                    Log.i("Retrofit", response.code().toString() + "")
                }
            }

            override fun onFailure(call: Call<Any?>, t: Throwable) {
                Log.i("Retrofit", "falha")
            }
        })
    }

    fun associarAll() {
        val call = RetrofitInitializer().getUsuario().associate()
        call?.enqueue(object : Callback<Void?> {
            override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                if (response.isSuccessful()) {
                    val temp: Any? = response.body()
                    Log.i("Retrofit", temp.toString())
                } else {
                    Log.i("Retrofit", response.code().toString() + "")
                }
            }

            override fun onFailure(call: Call<Void?>, t: Throwable) {
                Log.i("Retrofit", "falha")
            }
        })
    }
}