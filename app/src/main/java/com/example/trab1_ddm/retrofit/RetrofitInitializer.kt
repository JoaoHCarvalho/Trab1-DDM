package com.example.trab1_ddm.retrofit

import com.example.trab1_ddm.service.AcessoService
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class RetrofitInitializer {
    private var retrofit: Retrofit? = null

    fun RetrofitInitializer() {
        retrofit = Retrofit.Builder().baseUrl("https://api.steampowered.com/")
            .addConverterFactory(JacksonConverterFactory.create()).build()
    }

    fun getAcesso(): AcessoService {
        return retrofit!!.create(AcessoService::class.java)
    }
}