package com.example.trab1_ddm.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.trab1_ddm.model.Jogo

@Dao
interface JogoDAO {
    @Query("SELECT * FROM jogo")
    fun getAll(): List<Jogo?>?

    @Insert
    fun insertAll(vararg jogos: Jogo?)

    @Delete
    fun delete(jogo: Jogo?)

    @Query("SELECT * FROM jogo WHERE app_id = :appId")
    fun getByAppId(appId: Int): Jogo

    @Query("SELECT * FROM jogo WHERE nome = :nome")
    fun getByNome(nome: String): List<Jogo>

    @Query("SELECT * FROM jogo WHERE steam_id = :steamId")
    fun getBySteamId(steamId: String): List<Jogo>
}