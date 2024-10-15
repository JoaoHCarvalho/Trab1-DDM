package com.example.trab1_ddm.service

import com.example.trab1_ddm.model.Acesso
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AcessoService {
    @GET("{id}/json")
    fun select(@Path("id") id: Int): Call<Acesso?>?
}