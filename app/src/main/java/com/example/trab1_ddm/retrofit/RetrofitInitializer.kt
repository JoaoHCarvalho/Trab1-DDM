package com.example.trab1_ddm.retrofit

import com.example.trab1_ddm.service.AcessoService
import com.example.trab1_ddm.service.ConquistaService
import com.example.trab1_ddm.service.JogoService
import com.example.trab1_ddm.service.TrofeuService
import com.example.trab1_ddm.service.UsuarioJogoService
import com.example.trab1_ddm.service.UsuarioService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.create

class RetrofitInitializer {
    private var retrofit: Retrofit? = null

    var interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    var client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    fun RetrofitInitializer() {
        retrofit = Retrofit.Builder().baseUrl("https://api.steampowered.com/")
            .addConverterFactory(JacksonConverterFactory.create()).client(client).build()
    }

    fun getAcesso(): AcessoService {
        return retrofit!!.create(AcessoService::class.java)
    }
    fun getConquista(): ConquistaService{
        return retrofit!!.create(ConquistaService::class.java)
    }
    fun getJogo(): JogoService{
        return retrofit!!.create(JogoService::class.java)
    }
    fun getTrofeu(): TrofeuService{
        return retrofit!!.create(TrofeuService::class.java)
    }
    fun getUsuario(): UsuarioService{
        return retrofit!!.create(UsuarioService::class.java)
    }
    fun getUsuarioJogo(): UsuarioJogoService{
        return retrofit!!.create(UsuarioJogoService::class.java)
    }
}