package com.example.trab1_ddm.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.trab1_ddm.model.Conquista

@Dao
interface ConquistaDAO {
    @Query("SELECT * FROM conquista")
    fun getAll(): List<Conquista?>?

    @Insert
    fun insertAll(vararg conquistas: Conquista?)

    @Delete
    fun delete(conquista: Conquista?)

    @Query("SELECT * FROM conquista WHERE app_id = :appId")
    fun getByAppId(appId: Int): List<Conquista>

    @Query("SELECT * FROM conquista WHERE jogo_id = :jogoId")
    fun getByJogoId(jogoId: Int): List<Conquista>
}