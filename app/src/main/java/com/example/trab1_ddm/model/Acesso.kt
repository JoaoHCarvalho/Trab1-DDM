package com.example.trab1_ddm.model

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(
    tableName = "acesso",
    foreignKeys = [ForeignKey(
        entity = Usuario::class,
        parentColumns = ["id"],
        childColumns = ["usuario_id"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["usuario_id"])]
)
class Acesso {
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null

    @RequiresApi(Build.VERSION_CODES.O)
    @ColumnInfo(name = "data_acesso")
    val dataAcesso: LocalDateTime = LocalDateTime.now()

    @ColumnInfo(name = "nome_usuario")
    val nomeUsuario: String? = null

    @ColumnInfo(name = "usuario_id")
    val usuarioId: Int? = null
}