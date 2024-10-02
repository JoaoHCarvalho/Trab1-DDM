package com.example.trab1_ddm.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.Instant

@Entity
class Conquista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var conquistaId: Int? = null

    private var steamId: String? = null
    private var appId = 0
    private var nome: String? = null
    private var conquistaConcluida = 0
    private var unlockTime: Instant? = null
    private var imagem: String? = null // Adiciona o atributo imagem

    @ManyToOne
    @JoinColumn(name = "jogo_id")
    private var jogo: Jogo? = null

    fun getConquistaId(): Int? {
        return conquistaId
    }

    fun setConquistaId(conquistaId: Int?) {
        this.conquistaId = conquistaId
    }

    fun getSteamId(): String? {
        return steamId
    }

    fun setSteamId(steamId: String?) {
        this.steamId = steamId
    }

    fun getAppId(): Int {
        return appId
    }

    fun setAppId(appId: Int) {
        this.appId = appId
    }

    fun getNomeConquista(): String? {
        return nome
    }

    fun setNomeConquista(nomeConquista: String?) {
        this.nome = nomeConquista
    }

    fun getConquistaConcluida(): Int {
        return conquistaConcluida
    }

    fun setConquistaConcluida(conquistaConcluida: Int) {
        this.conquistaConcluida = conquistaConcluida
    }

    fun getUnlockTime(): Instant? {
        return unlockTime
    }

    fun setUnlockTime(unlockTime: Instant?) {
        this.unlockTime = unlockTime
    }

    fun getImagem(): String? {
        return imagem
    }

    fun setImagem(imagem: String?) {
        this.imagem = imagem
    }

    fun getJogo(): Jogo? {
        return jogo
    }

    fun setJogo(jogo: Jogo?) {
        this.jogo = jogo
    }
}