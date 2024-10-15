package com.example.trab1_ddm.dao

import androidx.room.Database
import com.example.trab1_ddm.model.*

@Database(entities = [Acesso::class, Conquista::class, Jogo::class, Trofeu::class, Usuario::class, UsuarioJogo::class], version = 1)
abstract class AppDatabase {
    abstract fun acessoDao(): AcessoDAO?
    abstract fun conquistaDao(): ConquistaDAO?
    abstract fun jogoDao(): JogoDAO?
    abstract fun trofeuDao(): TrofeuDAO?
    abstract fun usuarioDao(): UsuarioDAO?
    abstract fun usuarioJogoDao(): UsuarioJogoDAO?
}