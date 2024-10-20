package com.example.trab1_ddm.ViewModel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.trab1_ddm.R
import com.example.trab1_ddm.model.Conquista

class ConquistaAdapter(private val context: Context, private val conquistas: List<Conquista>) : ArrayAdapter<Conquista>(context, 0, conquistas) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.fragment__tela_detalhes_do_jogo, parent, false)

        val conquistasConcluidas = conquistas.count { it.conquistaConcluida == 1 }

        val conquistasNaoConcluidas = conquistas.size - conquistasConcluidas

        view.findViewById<TextView>(R.id.txt_adquiridas).text = "${conquistasConcluidas}/n Adquiridas"
        view.findViewById<TextView>(R.id.txt_disponivel).text =  "${conquistasNaoConcluidas}/n Disponíveis"

        return view
    }
}
