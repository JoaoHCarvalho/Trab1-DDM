package com.example.trab1_ddm.model

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import java.io.IOException
import java.net.URL
import java.util.function.Consumer


@Entity
class Jogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var jogoId: Int? = null

    private var appId = 0
    private var steamId: String? = null
    private var nome: String? = null
    private var tempoDeJogo = 0
    private var notaJogo: Int? = null
    private var jogoFavorito: Boolean? = null
    private var n_conquistas = 0
    private var f_conquistas = 0
    private var currentPlayers: Int? = null

    @OneToMany(mappedBy = "jogo")
    private var conquistasJogo: MutableList<Conquista>? = null

    @OneToMany(mappedBy = "jogo")
    private var usuarioJogos: Set<UsuarioJogo>? = null

    @OneToOne(mappedBy = "jogo")
    private var trofeu: Trofeu? = null

    fun getTrofeu(): Trofeu? {
        return trofeu
    }

    fun setTrofeu(trofeu: Trofeu?) {
        this.trofeu = trofeu
    }

    fun getUsuarioJogos(): Set<UsuarioJogo>? {
        return usuarioJogos
    }

    fun setUsuarioJogos(usuarioJogos: Set<UsuarioJogo>?) {
        this.usuarioJogos = usuarioJogos
    }

    fun getJogoId(): Int? {
        return jogoId
    }

    fun setJogoId(jogoId: Int?) {
        this.jogoId = jogoId
    }

    fun getAppId(): Int {
        return appId
    }

    fun setAppId(appId: Int) {
        this.appId = appId
    }

    fun getNome(): String? {
        return nome
    }

    fun setNome(nome: String?) {
        this.nome = nome
    }

    fun getTempoDeJogo(): Int {
        return tempoDeJogo
    }

    fun setTempoDeJogo(tempoDeJogo: Int) {
        this.tempoDeJogo = tempoDeJogo
    }

    fun getNotaJogo(): Int? {
        return notaJogo
    }

    fun setNotaJogo(notaJogo: Int?) {
        this.notaJogo = notaJogo
    }

    fun getJogoFavorito(): Boolean? {
        return jogoFavorito
    }

    fun setJogoFavorito(jogoFavorito: Boolean?) {
        this.jogoFavorito = jogoFavorito
    }

    fun getConquistasJogo(): List<Conquista>? {
        return conquistasJogo
    }

    fun setConquistasJogo(conquistasJogo: MutableList<Conquista>?) {
        this.conquistasJogo = conquistasJogo
    }

    fun getSteamId(): String? {
        return steamId
    }

    fun addConquistasJogo(conquista: Conquista) {
        conquistasJogo!!.add(conquista)
    }

    fun setSteamId(steamId: String?) {
        this.steamId = steamId
    }

    fun getN_conquistas(): Int {
        return n_conquistas
    }

    fun setN_conquistas(n_conquistas: Int) {
        this.n_conquistas = n_conquistas
    }

    fun getF_conquistas(): Int {
        return f_conquistas
    }

    fun setF_conquistas(f_conquistas: Int) {
        this.f_conquistas = f_conquistas
    }

    fun conquistasFinalizadas(conquistas: List<Conquista>) {
        conquistas.forEach { conquista ->
            if (conquista.getAppId() == getAppId() && conquista.getConquistaConcluida() == 1 && conquista.getSteamId() == steamId) {
                f_conquistas++
            }
        }
    }


    fun getCurrentPlayers(): Int? {
        return currentPlayers
    }

    fun setCurrentPlayers(currentPlayers: Int?) {
        this.currentPlayers = currentPlayers
    }

    fun atualizarCurrentPlayers() {
        val mapper = ObjectMapper()
        try {
            val url =
                URL("https://api.steampowered.com/ISteamUserStats/GetNumberOfCurrentPlayers/v1/?appid=$appId")
            val rootNode = mapper.readTree(url)

            // Acessa o valor de player_count na resposta JSON
            val responseNode = rootNode.path("response")
            if (!responseNode.isMissingNode) {
                val playerCount = responseNode.path("player_count").asInt()
                this.setCurrentPlayers(playerCount)
            } else {
                println("Resposta da API incompleta ou inválida.")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}