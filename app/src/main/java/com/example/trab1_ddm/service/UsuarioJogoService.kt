package com.example.trab1_ddm.service

import com.example.trab1_ddm.model.UsuarioJogo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UsuarioJogoService {
    @GET("{id}/json")
    fun select(@Path("id") id: Int): Call<UsuarioJogo?>?
}