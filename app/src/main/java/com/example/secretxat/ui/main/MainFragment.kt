package com.example.secretxat.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.secretxat.R
import com.example.secretxat.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

private lateinit var binding : FragmentMainBinding
private  val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)



        binding.btStart.setOnClickListener {

            if (!binding.etNickname.text.isNullOrEmpty()) {
                // Save the nickname
                viewModel.saveNickName(binding.etNickname.text.toString())
                // Navigate to the chat fragment
                findNavController().navigate(R.id.action_mainFragment_to_chatFragment)
            }



        }

        suscribeToState()

        // Inflate the layout for this fragment
        return binding.root
    }

    // Sfuncion para suscribirse al estado
    // de la vista
    // y actuar en consecuencia
    // en función del estado
    private fun suscribeToState() {
       lifecycleScope.launch {
           lifecycleScope.launch {
               viewModel.uiState.collect { state ->
                   when (state) {
                       MainViewState.LOADING -> {
                           binding.pbLoading.isVisible = true
                       }

                       MainViewState.REGISTERED -> {
                           findNavController().navigate(R.id.action_mainFragment_to_chatFragment)
                       }

                       MainViewState.UNREGISTERED -> {
                           binding.pbLoading.isVisible = false
                       }
                   }
               }
           }
       }
    }


}