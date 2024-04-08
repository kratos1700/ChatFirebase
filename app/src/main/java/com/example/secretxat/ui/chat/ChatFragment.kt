package com.example.secretxat.ui.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.secretxat.R
import com.example.secretxat.databinding.FragmentChatBinding
import com.example.secretxat.domain.model.MessageModel
import com.example.secretxat.ui.chat.adapter.ChatAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatFragment : Fragment() {


    private lateinit var binding: FragmentChatBinding
    private val viewModel: ChatViewModel by viewModels<ChatViewModel>()

    private lateinit var chatAdapter: ChatAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater, container, false)

        binding.ivBack.setOnClickListener {
            viewModel.disconnect() {

                findNavController().navigate(R.id.action_chatFragment_to_mainFragment)
            }
        }

        setUpUI()

        // Cuando se pulsa el botón de enviar mensaje
        //
        binding.btSendMsg.setOnClickListener {
            val msg: String = binding.etChat.text.toString()
            if (msg.isNotEmpty()) {

                viewModel.sendMessage(msg)
            }
            // Limpiar el campo de texto
            binding.etChat.text.clear()
        }


        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setUpUI() {
        setUpMsg()
        subscribeToMessages()
        setUpToolbar()

    }

    private fun setUpToolbar() {
        binding.tvName.text = viewModel.name
    }

    private fun setUpMsg() {
        chatAdapter = ChatAdapter(mutableListOf())
        binding.rvMsg.apply {
            adapter = chatAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun subscribeToMessages() {
        lifecycleScope.launch {
            viewModel.messageList.collect {
                setUpToolbar() // Aquí se actualiza el nombre del usuario
                // Aquí se actualiza la lista de mensajes
                chatAdapter.updateList(it.toMutableList(), viewModel.name)
                binding.rvMsg.scrollToPosition(chatAdapter.messageList.size - 1)

            }
        }
    }


}