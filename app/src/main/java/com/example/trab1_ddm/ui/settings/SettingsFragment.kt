package com.example.trab1_ddm.ui.settings

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.trab1_ddm.R
import com.example.trab1_ddm.ViewModel.UserViewModel
import com.example.trab1_ddm.databinding.FragmentTelaConfiguracoesBinding
import com.example.trab1_ddm.ui.settings.SettingsViewModel

class SettingsFragment : Fragment(){
    private var _binding: FragmentTelaConfiguracoesBinding? = null

    //This property is only valid between onCreateView and
    //onDestroyView.
    private val binding get() = _binding!!
    private lateinit var userViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SettingsViewModel::class.java)

        _binding = FragmentTelaConfiguracoesBinding.inflate(inflater, container, false)
        val root: View = binding.root
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        //val textView: TextView = binding.textSlideshow
        slideshowViewModel.text.observe(viewLifecycleOwner) {
            //textView.text = it
        }
        val buttonAdicionarFav = binding.buttonAdicionarFav
        val buttonAlterarApelido = binding.buttonAlterarApelido
        val buttonVincularSteam = binding.buttonVincularSteam

        // Listener para o botão "Alterar Senha"
        buttonAdicionarFav.setOnClickListener {
            // Cria o campo de texto para a nova senha
            val input = EditText(requireContext())
            input.hint = "Digite sua nova senha"

            AlertDialog.Builder(requireContext())
                .setTitle("Alterar Senha")
                .setView(input) // Adiciona o campo de texto ao AlertDialog
                .setPositiveButton("OK") { dialog, _ ->
                    val novaSenha = input.text.toString()

                    // Verifica se a senha não está vazia antes de enviar
                    if (novaSenha.isNotBlank()) {
                        // Chama o método do Retrofit com o usuário "erf" e a nova senha
                        userViewModel.changeSenha("erf", novaSenha)
                    } else {
                        // Mostra um alerta ou um feedback de que a senha não pode ser vazia
                        Toast.makeText(requireContext(), "A senha não pode ser vazia", Toast.LENGTH_SHORT).show()
                    }

                    dialog.dismiss()
                }
                .setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()
        }

        // Listener para o botão "Alterar Apelido"
        buttonAlterarApelido.setOnClickListener {
            val input = EditText(requireContext())
            input.hint = "Digite seu novo apelido"
            AlertDialog.Builder(requireContext())
                .setTitle("Alterar Apelido")
                .setView(input) // Adiciona o campo de texto ao AlertDialog
                .setPositiveButton("OK") { dialog, _ ->
                    val novoApelido = input.text.toString()

                    // Verifica se a senha não está vazia antes de enviar
                    if (novoApelido.isNotBlank()) {
                        // Chama o método do Retrofit com o usuário "erf" e a nova senha
                        userViewModel.changeApelido("erf", novoApelido)
                    } else {
                        // Mostra um alerta ou um feedback de que a senha não pode ser vazia
                        Toast.makeText(requireContext(), "O apelido não pode ser vazio", Toast.LENGTH_SHORT).show()
                    }

                    dialog.dismiss()
                }
                .setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()
        }

        // Listener para o botão "Vincular Conta"
        buttonVincularSteam.setOnClickListener {
            val input = EditText(requireContext())
            input.hint = "Insira seu steamID para vincular a conta"
            AlertDialog.Builder(requireContext())
                .setTitle("Vincular Conta Steam")
                .setView(input) // Adiciona o campo de texto ao AlertDialog
                .setPositiveButton("OK") { dialog, _ ->
                    val steamId = input.text.toString()

                    // Verifica se a senha não está vazia antes de enviar
                    if (steamId.isNotBlank()) {
                        // Chama o método do Retrofit com o usuário "erf" e a nova senha
                        userViewModel.assoSteamID(1, steamId)
                    } else {
                        // Mostra um alerta ou um feedback de que a senha não pode ser vazia
                        Toast.makeText(requireContext(), "O steamID não pode ser vazio", Toast.LENGTH_SHORT).show()
                    }

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