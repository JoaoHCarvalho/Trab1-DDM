package com.example.trab1_ddm.model

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.Table
import java.io.Serializable


@Entity
@Table(name = "usuario_jogo")
class UsuarioJogo : Serializable {
    @EmbeddedId
    var id: UsuarioJogoId? = null

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    var usuario: Usuario? = null

    @ManyToOne
    @MapsId("jogoId")
    @JoinColumn(name = "jogo_id")
    var jogo: Jogo? = null
}