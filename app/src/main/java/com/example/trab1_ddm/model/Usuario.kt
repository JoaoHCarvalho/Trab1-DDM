package com.example.trab1_ddm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(
    tableName = "usuario"
)
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("usuarioId")
    @ColumnInfo(name = "usuario_id")
    val usuarioId: Int? = null,

    @SerializedName("nomeUsuario")
    @ColumnInfo(name = "nome_usuario")
    val nomeUsuario: String? = null,

    @SerializedName("apelido")
    @ColumnInfo(name = "apelido")
    val apelido: String? = null,

    @SerializedName("senha")
    @ColumnInfo(name = "senha")
    val senha: String? = null,

    @SerializedName("steamId")
    @ColumnInfo(name = "steam_id")
    var steamId: String? = null,

    @SerializedName("rank")
    @ColumnInfo(name = "rank")
    val rank: Double = 0.0,

    @SerializedName("imagem")
    @ColumnInfo(name = "imagem")
    var imagem: String? = null
)