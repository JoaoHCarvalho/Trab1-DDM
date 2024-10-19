package com.example.trab1_ddm.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.trab1_ddm.model.Jogo
import com.example.trab1_ddm.retrofit.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel(){

    fun teste(s: String) {

        val call = RetrofitInitializer().getApi().selectJogos(s)
        call?.enqueue(object : Callback<List<Jogo>?> {
            override fun onResponse(call: Call<List<Jogo>?>, response: Response<List<Jogo>?>) {
                if (response.isSuccessful()) {
                    val cep: List<Jogo>? = response.body()
                    Log.i("Retrofit", cep.toString())
                } else {
                    Log.i("Retrofit", response.code().toString() + "")
                }
            }

            override fun onFailure(call: Call<List<Jogo>?>, t: Throwable) {
                Log.i("Retrofit", "falha")
            }
        })
    }
}