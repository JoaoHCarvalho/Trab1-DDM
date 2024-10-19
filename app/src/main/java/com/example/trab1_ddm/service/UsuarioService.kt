package com.example.trab1_ddm.service

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
    fun associate(): Call<List<Usuario>?>?

    @GET("user/{id}/{steamId}")
    fun assocSteam(@Path("id") id: Int, @Path("steamId") steamId: String): Call<Void?>?
}

data class UserRequest(
    val nomeUsuario: String,
    val apelido: String,
    val senha: String
)
