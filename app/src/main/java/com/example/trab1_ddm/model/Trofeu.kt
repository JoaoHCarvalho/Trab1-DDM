package com.example.trab1_ddm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "trofeu",
    foreignKeys = [ForeignKey(
        entity = Jogo::class,
        parentColumns = ["jogo_id"],
        childColumns = ["jogo_id"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["jogo_id"])]
)
data class Trofeu (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "trofeu_ouro")
    val trofeuOuro: Boolean? = null,

    @ColumnInfo(name = "trofeu_prata")
    val trofeuPrata: Boolean? = null,

    @ColumnInfo(name = "app_id")
    val appId: Int = 0,

    @ColumnInfo(name = "steam_id")
    val steamId: String? = null,

    @ColumnInfo(name = "jogo_id")
    val jogoId: Int? = null
)