package com.example.trab1_ddm.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trab1_ddm.model.Jogo
import com.example.trab1_ddm.retrofit.RetrofitInitializer
import com.example.trab1_ddm.service.UserRequest
import com.example.trab1_ddm.service.UsuarioService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel: ViewModel(){

    fun teste(s: String) {

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
}
