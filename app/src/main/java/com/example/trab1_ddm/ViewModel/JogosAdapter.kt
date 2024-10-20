package com.example.trab1_ddm.ViewModel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.trab1_ddm.R
import com.example.trab1_ddm.model.Jogo

class JogosAdapter(private val context: Context, private val jogos: List<Jogo>) : ArrayAdapter<Jogo>(context, 0, jogos) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val jogo = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_jogo, parent, false)

        // Exemplo de configuração do nome do jogo e do tempo de jogo
        view.findViewById<TextView>(R.id.nomeJogo).text = jogo?.nome
        view.findViewById<TextView>(R.id.tempoDeJogo).text = "${jogo?.tempoDeJogo} horas"

        return view
    }
}
