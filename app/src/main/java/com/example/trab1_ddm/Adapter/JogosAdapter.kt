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

class JogosAdapter(private val context: Context, private val jogos: List<Jogo>) : ArrayAdapter<Jogo>(context, 0, jogos) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val jogo = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_jogo, parent, false)

        // Referência ao TextView e ImageView
        val nomeJogoTextView = view.findViewById<TextView>(R.id.nomeJogo)
        val imageView = view.findViewById<ImageView>(R.id.img_concluido)
        view.findViewById<TextView>(R.id.tempoDeJogo).text = "${jogo?.tempoDeJogo} horas"
        // Define o nome do jogo
        nomeJogoTextView.text = jogo?.nome

        // Carrega a imagem usando o Glide, com base no appId do jogo
        jogo?.appId?.let { appId ->
            val imageUrl = "https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/$appId/capsule_184x69.jpg"
            Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_imgdetalhejogo) // Imagem de placeholder enquanto carrega
                .error(R.drawable.ic_launcher_imgdetalhejogo) // Imagem de erro caso a URL falhe
                .into(imageView)
        }

        return view
    }
}
