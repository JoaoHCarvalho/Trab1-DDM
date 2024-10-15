package com.example.trab1_ddm.model

import androidx.room.ColumnInfo
import java.util.Objects



data class UsuarioJogoId(
    @ColumnInfo(name = "usuario_id")
    var usuarioId: Int? = null,

    @ColumnInfo(name = "jogo_id")
    var jogoId: Int? = null
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        other as UsuarioJogoId
        return usuarioId == other.usuarioId && jogoId == other.jogoId
    }

    override fun hashCode(): Int {
        return Objects.hash(usuarioId, jogoId)
    }
}