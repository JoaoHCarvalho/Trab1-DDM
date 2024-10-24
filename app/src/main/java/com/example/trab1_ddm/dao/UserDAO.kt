package com.example.trab1_ddm.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.trab1_ddm.model.Usuario

class UserDAO(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "usuario_db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "usuario"

        // Nome das colunas
        private const val COLUMN_ID = "usuario_id"
        private const val COLUMN_NOME_USUARIO = "nome_usuario"
        private const val COLUMN_APELIDO = "apelido"
        private const val COLUMN_SENHA = "senha"
        private const val COLUMN_STEAM_ID = "steam_id"
        private const val COLUMN_RANK = "rank"
        private const val COLUMN_IMAGEM = "imagem"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NOME_USUARIO TEXT,
                $COLUMN_APELIDO TEXT,
                $COLUMN_SENHA TEXT,
                $COLUMN_STEAM_ID TEXT,
                $COLUMN_RANK REAL,
                $COLUMN_IMAGEM TEXT
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addUsuario(usuario: Usuario): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOME_USUARIO, usuario.nomeUsuario)
            put(COLUMN_APELIDO, usuario.apelido)
            put(COLUMN_SENHA, usuario.senha)
            put(COLUMN_STEAM_ID, usuario.steamId)
            put(COLUMN_RANK, usuario.rank)
            put(COLUMN_IMAGEM, usuario.imagem)
        }
        val id = db.insert(TABLE_NAME, null, values)
        db.close()
        return id
    }

    fun getUsuarioById(id: Int): Usuario? {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_NAME, null, "$COLUMN_ID=?", arrayOf(id.toString()),
            null, null, null
        )
        var usuario: Usuario? = null
        if (cursor.moveToFirst()) {
            usuario = cursorToUsuario(cursor)
        }
        cursor.close()
        db.close()
        return usuario
    }

    fun getAllUsuarios(): List<Usuario> {
        val usuarios = mutableListOf<Usuario>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        if (cursor.moveToFirst()) {
            do {
                val usuario = cursorToUsuario(cursor)
                usuarios.add(usuario)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return usuarios
    }

    fun updateUsuario(usuario: Usuario): Int {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOME_USUARIO, usuario.nomeUsuario)
            put(COLUMN_APELIDO, usuario.apelido)
            put(COLUMN_SENHA, usuario.senha)
            put(COLUMN_STEAM_ID, usuario.steamId)
            put(COLUMN_RANK, usuario.rank)
            put(COLUMN_IMAGEM, usuario.imagem)
        }
        val rowsAffected = db.update(TABLE_NAME, values, "$COLUMN_ID=?", arrayOf(usuario.usuarioId.toString()))
        db.close()
        return rowsAffected
    }

    fun deleteUsuario(id: Int): Int {
        val db = writableDatabase
        val rowsDeleted = db.delete(TABLE_NAME, "$COLUMN_ID=?", arrayOf(id.toString()))
        db.close()
        return rowsDeleted
    }

    fun resetarIdUsuario() {
        val db = writableDatabase
        db.execSQL("DELETE FROM sqlite_sequence WHERE name='$TABLE_NAME'")
        db.close()
    }


    private fun cursorToUsuario(cursor: Cursor): Usuario {
        return Usuario(
            usuarioId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
            nomeUsuario = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOME_USUARIO)),
            apelido = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_APELIDO)),
            senha = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SENHA)),
            steamId = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STEAM_ID)),
            rank = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_RANK)),
            imagem = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGEM))
        )
    }

    fun getSteamIdById(id: Int): String? {
        val db = readableDatabase
        var steamId: String? = null

        val cursor = db.query(
            TABLE_NAME,
            arrayOf(COLUMN_STEAM_ID),
            "$COLUMN_ID = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )

        if (cursor.moveToFirst()) {
            steamId = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_STEAM_ID))
        }

        cursor.close()
        db.close()

        return steamId
    }
    fun getNomeById(id: Int): String? {
        val db = readableDatabase
        var steamId: String? = null

        val cursor = db.query(
            TABLE_NAME,
            arrayOf(COLUMN_NOME_USUARIO),
            "$COLUMN_ID = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )

        if (cursor.moveToFirst()) {
            steamId = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOME_USUARIO))
        }

        cursor.close()
        db.close()

        return steamId
    }
    fun deleteAllUsuarios(): Int {
        val db = writableDatabase
        val rowsDeleted = db.delete(TABLE_NAME, null, null)
        db.close()
        return rowsDeleted
    }

}
