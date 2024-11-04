package com.example.trab1_ddm.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.trab1_ddm.R
import com.example.trab1_ddm.model.Jogo

class JogosConcluidosAdapter(private val context: Context, private val jogos: List<Jogo>) : ArrayAdapter<Jogo>(context, 0, jogos) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val jogo = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_jogo_concluido, parent, false)

        view.findViewById<TextView>(R.id.nomeJogo).text = jogo?.nome
        view.findViewById<TextView>(R.id.totalConquistas).text = "Conquistas: ${jogo?.n_conquistas}"
        val imageView = view.findViewById<ImageView>(R.id.img_temp)
        jogo?.appId?.let { appId ->
            val imageUrl = "https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/$appId/capsule_184x69.jpg"
            Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_imgdetalhejogo)
                .error(R.drawable.ic_launcher_imgdetalhejogo)
                .into(imageView)
        }
        return view
    }
}