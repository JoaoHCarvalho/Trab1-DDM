package com.example.trab1_ddm.ui.settings

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.trab1_ddm.R
import com.example.trab1_ddm.databinding.FragmentTelaConfiguracoesBinding
import com.example.trab1_ddm.ui.settings.SettingsViewModel

class SettingsFragment : Fragment(){
    private var _binding: FragmentTelaConfiguracoesBinding? = null

    //This property is only valid between onCreateView and
    //onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SettingsViewModel::class.java)

        _binding = FragmentTelaConfiguracoesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //val textView: TextView = binding.textSlideshow
        slideshowViewModel.text.observe(viewLifecycleOwner) {
            //textView.text = it
        }
        val buttonAdicionarFav = view?.findViewById<Button>(R.id.button_adicionar_fav)
        val buttonAlterarApelido = view?.findViewById<Button>(R.id.button_alterar_apelido)
        val buttonVincularSteam = view?.findViewById<Button>(R.id.button_vincular_steam)

        // Listener para o botão "Alterar Senha"
        buttonAdicionarFav?.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Alterar Senha")
                .setMessage("Insira sua nova senha:")
                .setView(EditText(requireContext())) // Adiciona um campo de texto para inserir a nova senha
                .setPositiveButton("Alterar") { dialog, _ ->
                    // Lógica para alterar a senha
                    dialog.dismiss()
                }
                .setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()
        }

        // Listener para o botão "Alterar Apelido"
        buttonAlterarApelido?.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Alterar Apelido")
                .setMessage("Insira seu novo apelido:")
                .setView(EditText(requireContext())) // Adiciona um campo de texto para inserir o novo apelido
                .setPositiveButton("Alterar") { dialog, _ ->
                    // Lógica para alterar o apelido
                    dialog.dismiss()
                }
                .setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()
        }

        // Listener para o botão "Vincular Conta"
        buttonVincularSteam?.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Vincular Conta Steam")
                .setMessage("Insira seu ID Steam para vincular a conta:")
                .setView(EditText(requireContext())) // Adiciona um campo de texto para inserir o ID Steam
                .setPositiveButton("Vincular") { dialog, _ ->
                    // Lógica para vincular a conta
                    dialog.dismiss()
                }
                .setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}