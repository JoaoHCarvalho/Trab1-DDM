package com.example.trab1_ddm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "usuario"
)
data class Usuario (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "usuario_id")
    val usuarioId: Int? = null,

    @ColumnInfo(name = "nome_usuario")
    val nomeUsuario: String? = null,

    @ColumnInfo(name = "apelido")
    val apelido: String? = null,

    @ColumnInfo(name = "senha")
    val senha: String? = null,

    @ColumnInfo(name = "steam_id")
    val steamId: String? = null,

    @ColumnInfo(name = "rank")
    val rank: Double = 0.0,

    @ColumnInfo(name = "imagem")
    val imagem: String? = null
)