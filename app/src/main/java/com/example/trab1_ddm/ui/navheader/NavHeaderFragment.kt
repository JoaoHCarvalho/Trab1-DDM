package com.example.trab1_ddm.ui.navheader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.trab1_ddm.R
import com.example.trab1_ddm.dao.UserDAO
import com.example.trab1_ddm.model.Usuario

class NavHeaderFragment : Fragment() {

    private lateinit var imageView: ImageView
    private lateinit var userDAO: UserDAO

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.nav_header_main, container, false)
        imageView = view.findViewById(R.id.imageView)

        userDAO = UserDAO(requireContext())

        // Substitua `userId` pelo ID do usuário que você quer buscar a imagem.
        val userId = 1
        val usuario = userDAO.getUsuarioById(userId)
        usuario?.let {
            exibirImagem(it)
        }

        return view
    }

    private fun exibirImagem(usuario: Usuario) {
        val imagemPath = usuario.imagem
        if (!imagemPath.isNullOrEmpty()) {
            val bitmap = BitmapFactory.decodeFile(imagemPath)
            imageView.setImageBitmap(bitmap)
        } else {
            Log.e("NavHeaderFragment", "Caminho da imagem está vazio ou nulo")
        }
    }
}

