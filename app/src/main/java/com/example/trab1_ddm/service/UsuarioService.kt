package com.example.trab1_ddm.service

import com.example.trab1_ddm.model.Jogo
import com.example.trab1_ddm.model.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UsuarioService {
    @GET("user/{steamId}/imagemPerfil")
    fun select(@Path("steamId") steamId: String): Call<Any?>?

    @POST("user/create")
    fun createUser(@Body userRequest: UserRequest): Call<Any?>?

    @GET("user/associar")
    fun associate(): Call<Void?>?

    @GET("user/{id}/{steamId}")
    fun assocSteam(@Path("id") id: Int, @Path("steamId") steamId: String): Call<Void?>?

    @GET("user/steamId/{steamId}/jogos")
    fun getGames(@Path("steamId")steamId: String): Call<List<Jogo>?>?

    @GET("user/all")
    fun allUsers(): Call<List<Usuario>?>?

    @GET("user/nome/{username}/senha/{senha}")
    fun setNewSenha(@Path("username") username: String, @Path("senha") senha: String): Call<Usuario>

    @GET("user/nome/{username}/apelido/{apelido}")
    fun setNewApelido(@Path("username") username: String, @Path("apelido") apelido: String): Call<Usuario>

    @GET("user/nome/{username}")
    fun getUser(@Path("username") username: String): Call<Usuario>

    @GET("user/nome/{username}/steamId/{steamId}")
    fun setSteamByName(@Path("username") username: String, @Path("steamId") steamId: String): Call<Usuario>
}

data class UserRequest(
    val nomeUsuario: String,
    val apelido: String,
    val senha: String
)
