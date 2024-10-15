package com.example.trab1_ddm.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.trab1_ddm.model.UsuarioJogo

interface UsuarioJogoDAO {
    @Query("SELECT * FROM usuario_jogo")
    fun getAll(): List<UsuarioJogo?>?

    @Insert
    fun insertAll(vararg usuariosJ: UsuarioJogo?)

    @Delete
    fun delete(usuarioJ: UsuarioJogo?)

}