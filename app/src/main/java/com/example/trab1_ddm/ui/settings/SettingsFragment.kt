package com.example.trab1_ddm.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}