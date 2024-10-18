package com.example.trab1_ddm.service

import com.example.trab1_ddm.model.Conquista
import com.example.trab1_ddm.model.Jogo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface JogoService {
    @GET("jogo/all")
    fun selectAll(): Call<List<Jogo>?>?

    @GET("jogo/{id}")
    fun selectJogo(@Path("id") id: Int): Call<Jogo?>?

    @GET("jogo/nome/{name}")
    fun selectJogoNome(@Path("name") name: String): Call<Jogo?>?

    @GET("jogo/{id}/notaJogo/{nota}")
    fun notaJogo(@Path("id") id: Int, @Path("nota") nota: Int): Call<Int?>?

    @GET("jogo/nome/{name}/{steamId}")
    fun searchJogo(@Path("name") name: String, @Path("steamId") steamId: String): Call<Jogo?>?

    @GET("jogo/jogadoresAtual/{appId}/{steamId}")
    fun playerAtual(@Path("appId") appId: Int, @Path("steamId") steamId: String): Call<Int?>?

    @GET("jogo/{id}/conquistas")
    fun jogoConquista(@Path("id") id: Int): Call<List<Conquista>?>?

    @GET("jogo/{id}/jogoFavorito")
    fun jogoFavorito(@Path("id") id: Int): Call<Boolean?>?
}