package com.example.trab1_ddm.dao

import androidx.room.Database
import com.example.trab1_ddm.model.Acesso

@Database(entities = [Acesso::class], version = 1)
abstract class AppDatabase {
    abstract fun acessoDao(): AcessoDAO?
}