package com.example.trab1_ddm.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory : ViewModelProvider.Factory{
     override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            UserViewModel() as T
        } else {
            throw IllegalArgumentException("Classe ViewModel desconhecida: ${modelClass.name}")
        }
    }
}