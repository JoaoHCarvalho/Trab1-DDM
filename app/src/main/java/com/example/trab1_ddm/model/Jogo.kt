package com.example.trab1_ddm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties


@Entity(
    tableName = "jogo"
)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Jogo (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "jogo_id")
    val jogoId: Int? = null,

    @ColumnInfo(name = "app_id")
    val appId: Int = 0,

    @ColumnInfo(name = "steam_id")
    val steamId: String? = null,

    @ColumnInfo(name = "nome")
    val nome: String? = null,

    @ColumnInfo(name = "tempo_de_jogo")
    val tempoDeJogo: Int = 0,

    @ColumnInfo(name = "nota_jogo")
    val notaJogo: Int? = null,

    @ColumnInfo(name = "jogo_favorito")
    val jogoFavorito: Boolean? = null,

    @ColumnInfo(name = "n_conquistas")
    val n_conquistas: Int = 0,

    @ColumnInfo(name = "f_conquistas")
    val f_conquistas: Int = 0,

    @ColumnInfo(name = "current_players")
    val currentPlayers: Int? = null
)