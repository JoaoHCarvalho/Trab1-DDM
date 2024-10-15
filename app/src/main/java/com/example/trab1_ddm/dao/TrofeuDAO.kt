package com.example.trab1_ddm.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.trab1_ddm.model.Trofeu

@Dao
interface TrofeuDAO {
    @Query("SELECT * FROM trofeu")
    fun getAll(): List<Trofeu?>?

    @Insert
    fun insertAll(vararg trofeus: Trofeu?)

    @Delete
    fun delete(trofeu: Trofeu?)

    @Query("SELECT * FROM trofeu WHERE app_id = :appId")
    fun getByAppId(appId: Int): Trofeu

    @Query("SELECT * FROM trofeu WHERE jogo_id = :jogoId")
    fun getByJogoId(jogoId: Int): Trofeu

    @Query("SELECT * FROM trofeu WHERE steam_id = :steamId")
    fun getBySteamId(steamId: String): List<Trofeu>
}