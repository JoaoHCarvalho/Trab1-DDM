package com.example.trab1_ddm.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(
    tableName = "conquista",
    foreignKeys = [ForeignKey(
        entity = Jogo::class,
        parentColumns = ["jogo_id"],
        childColumns = ["jogo_id"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["jogo_id"])]
)
class Conquista {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "conquista_id")
    val conquistaId: Int? = null

    @ColumnInfo(name = "steam_id")
    val steamId: String? = null

    @ColumnInfo(name = "app_id")
    val appId: Int = 0

    @ColumnInfo(name = "nome")
    val nome: String? = null

    @ColumnInfo(name = "conquista_concluida")
    val conquistaConcluida: Int = 0

    @ColumnInfo(name = "unlock_time")
    val unlockTime: Instant? = null

    @ColumnInfo(name = "imagem")
    val imagem: String? = null

    @ColumnInfo(name = "jogo_id")
    val jogoId: Int? = null
}