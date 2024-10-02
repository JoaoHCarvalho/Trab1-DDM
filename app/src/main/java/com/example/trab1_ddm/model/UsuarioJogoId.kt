package com.example.trab1_ddm.model

import jakarta.persistence.Embeddable
import java.util.Objects


@Embeddable
class UsuarioJogoId {
    var usuarioId: Int? = null
    var jogoId: Int? = null

    constructor()

    constructor(usuarioId: Int?, jogoId: Int?) {
        this.usuarioId = usuarioId
        this.jogoId = jogoId
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as UsuarioJogoId
        return usuarioId == that.usuarioId && jogoId == that.jogoId
    }

    override fun hashCode(): Int {
        return Objects.hash(usuarioId, jogoId)
    }
}