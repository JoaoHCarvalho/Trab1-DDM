package com.example.trab1_ddm.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne

@Entity
class Trofeu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Int? = null

    @OneToOne
    @JoinColumn(name = "jogo_id")
    private var jogo: Jogo? = null

    private var trofeuOuro: Boolean? = null

    private var trofeuPrata: Boolean? = null
    private var appId = 0
    private var steamId: String? = null

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getJogo(): Jogo? {
        return jogo
    }

    fun setJogo(jogo: Jogo?) {
        this.jogo = jogo
    }

    fun getTrofeuOuro(): Boolean? {
        return trofeuOuro
    }

    fun setTrofeuOuro(trofeuOuro: Boolean?) {
        this.trofeuOuro = trofeuOuro
    }

    fun getTrofeuPrata(): Boolean? {
        return trofeuPrata
    }

    fun setTrofeuPrata(trofeuPrata: Boolean?) {
        this.trofeuPrata = trofeuPrata
    }

    fun getAppId(): Int {
        return appId
    }

    fun setAppId(appId: Int) {
        this.appId = appId
    }

    fun getSteamId(): String? {
        return steamId
    }

    fun setSteamId(steamId: String?) {
        this.steamId = steamId
    }

    fun reinvidicarTrofeu() {
        //usuario controller
    }
}