package com.example.trab1_ddm.ui.settings

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.trab1_ddm.dao.UserDAO
import com.example.trab1_ddm.databinding.FragmentTelaConfiguracoesBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SettingsFragment : Fragment(){
    private var _binding: FragmentTelaConfiguracoesBinding? = null

    private val binding get() = _binding!!
    private lateinit var settingsViewModel: SettingsViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val usuarioDao = UserDAO(requireContext())
        _binding = FragmentTelaConfiguracoesBinding.inflate(inflater, container, false)
        val root: View = binding.root
        settingsViewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)


        val buttonAdicionarFav = binding.buttonAdicionarFav
        val buttonAlterarApelido = binding.buttonAlterarApelido
        val buttonVincularSteam = binding.buttonVincularSteam
        val buttonSairConta = binding.buttonSairConta


        buttonAdicionarFav.setOnClickListener {

            val input = EditText(requireContext())
            input.hint = "Digite sua nova senha"

            AlertDialog.Builder(requireContext())
                .setTitle("Alterar Senha")
                .setView(input)
                .setPositiveButton("OK") { dialog, _ ->
                    val novaSenha = input.text.toString()

                    if (novaSenha.isNotBlank()) {
                        usuarioDao.getNomeById(1)?.let { it1 ->
                            settingsViewModel.changeSenha(it1, novaSenha) {
                                    usuario ->
                                if(usuario != null){
                                    usuarioDao.getUsuarioById(1)?.let { usuario ->
                                        val usuarioAtualizado = usuario.copy(senha = novaSenha)

                                        val rowsUpdated = usuarioDao.updateUsuario(usuarioAtualizado)
                                        if (rowsUpdated > 0) {
                                            Toast.makeText(
                                                requireContext(),
                                                "Senha alterada com sucesso!",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        } else {
                                            Toast.makeText(
                                                requireContext(),
                                                "Erro ao atualizar o usuário",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                } else {
                                    Toast.makeText(
                                        requireContext(),
                                        "Erro",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    } else {
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


        buttonAlterarApelido.setOnClickListener {
            val input = EditText(requireContext())
            input.hint = "Digite seu novo apelido"
            AlertDialog.Builder(requireContext())
                .setTitle("Alterar Apelido")
                .setView(input)
                .setPositiveButton("OK") { dialog, _ ->
                    val novoApelido = input.text.toString()
                    if (novoApelido.isNotBlank()) {
                        usuarioDao.getNomeById(1)?.let { it1 ->
                            settingsViewModel.changeApelido(it1, novoApelido) {
                                    usuario ->
                                if(usuario != null){
                                    usuarioDao.getUsuarioById(1)?.let { usuario ->
                                        val usuarioAtualizado = usuario.copy(apelido = novoApelido)

                                        val rowsUpdated = usuarioDao.updateUsuario(usuarioAtualizado)
                                        if (rowsUpdated > 0) {
                                            Toast.makeText(
                                                requireContext(),
                                                "Apelido alterado com sucesso",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        } else {
                                            Toast.makeText(
                                                requireContext(),
                                                "Erro ao atualizar o usuário",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                } else {
                                    Toast.makeText(
                                        requireContext(),
                                        "Erro",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    } else {
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

        buttonVincularSteam.setOnClickListener {
            val input = EditText(requireContext())
            input.hint = "Insira seu steamID para vincular a conta"
            AlertDialog.Builder(requireContext())
                .setTitle("Vincular Conta Steam")
                .setView(input)
                .setPositiveButton("OK") { dialog, _ ->
                    val steamId = input.text.toString()
                    println("asdasd")
                    if (steamId.isNotBlank()) {
                        usuarioDao.getNomeById(1)?.let { it1 ->
                            settingsViewModel.setSteamId(it1, steamId) {
                                    usuario ->
                                if(usuario != null){
                                    usuarioDao.getUsuarioById(1)?.let { usuario ->
                                        val usuarioAtualizado = usuario.copy(steamId = steamId)

                                        usuarioDao.updateUsuario(usuarioAtualizado)
                                        lifecycleScope.launch {
                                            try {
                                                settingsViewModel.getJogos(steamId)
                                                delay(10000)
                                                settingsViewModel.selectConq(steamId)
                                                delay(10000)
                                                settingsViewModel.setConq(steamId)
                                                delay(10000)
                                                settingsViewModel.setTrofeu(steamId)
                                                delay(10000)
                                                settingsViewModel.associarAll()

                                                Toast.makeText(
                                                    requireContext(),
                                                    "Vinculação concluída com sucesso!",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            } catch (e: Exception) {
                                                Toast.makeText(
                                                    requireContext(),
                                                    "Erro ao vincular a conta Steam",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }
                                    }
                                } else {
                                    Toast.makeText(
                                        requireContext(),
                                        "Erro",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }

                    } else {
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
        buttonSairConta.setOnClickListener {
            usuarioDao.deleteAllUsuarios()
            usuarioDao.resetarIdUsuario()
            Toast.makeText(requireContext(), "Logoff efetuado com sucesso", Toast.LENGTH_SHORT).show()
        }
        return root
    }

    private fun bruh(steamId: String) {
        settingsViewModel.selectConq(steamId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}