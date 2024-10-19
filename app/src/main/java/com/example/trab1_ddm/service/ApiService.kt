package com.example.trab1_ddm.service

import com.example.trab1_ddm.model.Jogo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/playerGames/{steamId}")
    fun selectJogos(@Path("steamId") steamId: String): Call<Any?>?

    @GET("api/deleteEmpty")
    fun deleteEmpty(): Call<Any?>?

    @GET("api/executeCommands2/{steamId}")
    fun selectConquista(@Path("steamId") steamId: String): Call<String?>?

    @GET("api/set/{steamId}")
    fun associarJogoConq(@Path("steamId") steamId: String): Call<Any?>?

    @GET("api/setTrofeus/{steamId}")
    fun setTrofeus(@Path("steamId")steamId: String): Call<Any?>?
}