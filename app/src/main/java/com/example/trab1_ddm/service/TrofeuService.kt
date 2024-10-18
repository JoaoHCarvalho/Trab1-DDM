package com.example.trab1_ddm.service

import com.example.trab1_ddm.model.Trofeu
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TrofeuService {
    @GET("trofeu/get/{steamId}")
    fun getTrofeus(@Path("steamId") steamId: String): Call<List<Trofeu>?>?

}