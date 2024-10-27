import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.trab1_ddm.R

class GameDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment__tela_detalhes_do_jogo, container, false)

        // Recuperar os dados do jogo do bundle
        val nomeJogo = arguments?.getString("NomedoJogo")
        val conquistasJogo = arguments?.getString("Conquistadas:")
        val conquistasDisJogo = arguments?.getString("Total:")
        val appidJogo = arguments?.getString("appid")

        // Exibir os dados nos TextViews
        val nomeTextView: TextView = view.findViewById(R.id.title_jogo_detalhe)
        val conquistaTextView: TextView = view.findViewById(R.id.txt_adquiridas)
        val conquistaDisTextView: TextView = view.findViewById(R.id.txt_disponivel)

        nomeTextView.text = nomeJogo
        conquistaTextView.text = "Concluídas: \n $conquistasJogo"
        conquistaDisTextView.text = "Total: \n $conquistasDisJogo"

        // Carregar a imagem do jogo no ImageView
        val imageView: ImageView = view.findViewById(R.id.img_porc_concluido)
        appidJogo?.let { appId ->
            val imageUrl = "https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/$appId/capsule_184x69.jpg"
            Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_imgdetalhejogo) // imagem de placeholder enquanto carrega
                .error(R.drawable.ic_launcher_imgdetalhejogo) // imagem de erro caso a URL falhe
                .into(imageView)
        }

        return view
    }
}

