package com.example.trab1_ddm.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.trab1_ddm.model.Usuario

@Dao
interface UsuarioDAO {
    @Query("SELECT * FROM usuario")
    fun getAll(): List<Usuario?>?

    @Insert
    fun insertAll(vararg usuarios: Usuario?)

    @Delete
    fun delete(usuario: Usuario?)

    @Query("SELECT * FROM usuario WHERE nome_usuario = :nome")
    fun getByNome(nome: String): Usuario

    @Query("SELECT * FROM usuario WHERE steam_id = :steamId")
    fun getBySteamId(steamId: String): Usuario
}