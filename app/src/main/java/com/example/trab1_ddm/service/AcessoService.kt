package com.example.trab1_ddm.service

import com.example.trab1_ddm.model.Acesso
import com.example.trab1_ddm.model.Usuario
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AcessoService {
    @GET("acesso/current/{nomeUsuario}")
    fun select(@Path("nomeUsuario") nomeUsuario: String): Call<Acesso?>?
}