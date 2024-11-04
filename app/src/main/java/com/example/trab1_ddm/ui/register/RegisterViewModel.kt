package com.example.trab1_ddm.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trab1_ddm.retrofit.RetrofitInitializer
import com.example.trab1_ddm.service.UserRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {

    fun createUser(nomeusuario: String, apelido: String, senha: String) {

        val call =
            RetrofitInitializer().getUsuario().createUser(UserRequest(nomeusuario, apelido, senha))
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
}