package com.example.trab1_ddm.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.trab1_ddm.model.Acesso

@Dao
interface AcessoDAO {
    @Query("SELECT * FROM acesso")
    fun getAll(): List<Acesso?>?

    @Insert
    fun insertAll(vararg ceps: Acesso?)

    @Delete
    fun delete(cep: Acesso?)
}