package com.example.trab1_ddm.model

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import java.io.IOException
import java.net.URL


@Entity
class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var usuarioId: Int? = null


    private var nomeUsuario: String? = null
    private var apelido: String? = null
    private var senha: String? = null
    private var steamId: String? = null
    private var rank = 0.0
    private var imagem: String? = null

    @OneToMany(mappedBy = "usuario")
    private var acessos: List<Acesso>? = null

    @OneToMany(mappedBy = "usuario")
    private var usuarioJogos: Set<UsuarioJogo>? = null

    fun getUsuarioId(): Int? {
        return usuarioId
    }

    fun setUsuarioId(usuarioId: Int?) {
        this.usuarioId = usuarioId
    }

    fun getUsuarioJogos(): Set<UsuarioJogo>? {
        return usuarioJogos
    }

    fun setUsuarioJogos(usuarioJogos: Set<UsuarioJogo>?) {
        this.usuarioJogos = usuarioJogos
    }

    fun getId(): Int? {
        return usuarioId
    }

    fun setId(id: Int?) {
        this.usuarioId = id
    }

    fun getNomeUsuario(): String? {
        return nomeUsuario
    }

    fun setNomeUsuario(nomeUsuario: String?) {
        this.nomeUsuario = nomeUsuario
    }

    fun getApelido(): String? {
        return apelido
    }

    fun setApelido(apelido: String?) {
        this.apelido = apelido
    }

    fun getSenha(): String? {
        return senha
    }

    fun setSenha(senha: String?) {
        this.senha = senha
    }

    fun getAcessos(): List<Acesso>? {
        return acessos
    }

    fun setAcessos(acessos: List<Acesso>?) {
        this.acessos = acessos
    }

    fun getSteamId(): String? {
        return steamId
    }

    fun setSteamId(steamId: String?) {
        this.steamId = steamId
    }

    fun getRank(): Double {
        return rank
    }

    fun setRank(rank: Double) {
        this.rank = rank
    }

    fun getImagem(): String? {
        return imagem
    }

    fun setImagem(imagem: String?) {
        this.imagem = imagem
    }

    fun atualizarImagem(): Usuario? {
        val mapper = ObjectMapper()
        try {
            val url =
                URL("https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v2/?key=9D94F49413553413A449F22760F36A56&steamids=$steamId")
            val rootNode = mapper.readTree(url)

            // Acessa o valor de players na resposta JSON
            val playersNode = rootNode.path("response").path("players")
            if (playersNode.isArray && playersNode.size() > 0) {
                val playerNode = playersNode[0]
                val avatarFull = playerNode.path("avatarfull").asText()
                this.setImagem(avatarFull)
                return this
            } else {
                println("Resposta da API incompleta ou inválida.")
                return null
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    fun conectarSteam() {
        // UsuarioController
    }

    fun ocultarJogo(jogo: Jogo?) {
    }

    fun alterarApelido() {
        // UsuarioController
    }

    fun editarNotaJogo(jogo: Jogo?) {
        // JogoController
    }

    fun defineNotaJogo(jogo: Jogo?) {
        // JogoController
    }

    fun exibeConquistaJogo(jogo: Jogo?) {
        // JogoController
    }
}