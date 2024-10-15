package com.example.trab1_ddm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import java.io.Serializable


@Entity(
    tableName = "usuario_jogo",
    primaryKeys = ["usuario_id", "jogo_id"],
    foreignKeys = [
        ForeignKey(
            entity = Usuario::class,
            parentColumns = ["usuario_id"],
            childColumns = ["usuario_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Jogo::class,
            parentColumns = ["jogo_id"],
            childColumns = ["jogo_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["usuario_id"]), Index(value = ["jogo_id"])]
)
class UsuarioJogo : Serializable {
    @ColumnInfo(name = "usuario_id")
    val usuarioId: Int? = null

    @ColumnInfo(name = "jogo_id")
    val jogoId: Int? = null
}