package com.example.trab1_ddm.model

import android.annotation.TargetApi
import android.os.Build
import jakarta.persistence.*
import java.time.LocalDateTime

//Perguntar da API
//Perguntar de como fazer a "controler"
//Se tem que usar o Retrofit
//Persistencia de dados

@Entity
class Acesso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Int? = null


    private var dataAcesso: LocalDateTime? = null
    private var nomeUsuario: String? = null

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private var usuario: Usuario? = null
    @TargetApi(Build.VERSION_CODES.O)
    @PrePersist
    fun onCreate() {
        this.dataAcesso = LocalDateTime.now()
    }

    fun Acesso() {
    }

    fun Acesso(id: Int?, dataAcesso: LocalDateTime?, usuario: Usuario?) {
        this.id = id
        this.dataAcesso = dataAcesso
        this.usuario = usuario
    }

    fun getNomeUsuario(): String? {
        return nomeUsuario
    }

    fun setNomeUsuario(nomeUsuario: String?) {
        this.nomeUsuario = nomeUsuario
    }

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getDataAcesso(): LocalDateTime? {
        return dataAcesso
    }

    fun setDataAcesso(dataAcesso: LocalDateTime?) {
        this.dataAcesso = dataAcesso
    }

    fun getUsuario(): Usuario? {
        return usuario
    }

    fun setUsuario(usuario: Usuario?) {
        this.usuario = usuario
    }

    fun atualizarRankingJogador() {
        //usuario controller
    }

    fun atualizaTempoLogin() {
        //acesso controller
    }
}