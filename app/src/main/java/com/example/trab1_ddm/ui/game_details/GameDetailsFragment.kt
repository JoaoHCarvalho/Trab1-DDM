import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
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

        // Exibir os dados nos TextViews
        val nomeTextView: TextView = view.findViewById(R.id.title_jogo_detalhe)
        val conquistaTextView: TextView = view.findViewById(R.id.txt_adquiridas)
        val conquistaDisTextView: TextView = view.findViewById(R.id.txt_disponivel)

        nomeTextView.text = nomeJogo
        conquistaTextView.text = "Concluidas: \n $conquistasJogo"
        conquistaDisTextView.text = "Total: \n $conquistasDisJogo"
        return view
    }
}
