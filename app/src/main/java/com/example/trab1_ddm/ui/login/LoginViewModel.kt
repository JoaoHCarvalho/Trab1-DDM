package com.example.trab1_ddm.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.trab1_ddm.model.Usuario
import com.example.trab1_ddm.retrofit.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel: ViewModel() {
    fun getUserByName(user: String, callback: (Usuario?) -> Unit) {
        val call = RetrofitInitializer().getUsuario().getUser(user)
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
}