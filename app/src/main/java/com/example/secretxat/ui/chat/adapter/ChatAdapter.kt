package com.example.secretxat.ui.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.secretxat.databinding.ItemChatMeBinding
import com.example.secretxat.databinding.ItemChatOtherBinding
import com.example.secretxat.domain.model.MessageModel

class ChatAdapter( var messageList: MutableList<MessageModel>, private var userName: String = "") :
    RecyclerView.Adapter<ChatViewHolder>() {

    companion object {
         const val MESSAGE_SENT = 0
         const val MESSAGE_RECEIVED = 1
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding = when(viewType){
            MESSAGE_SENT -> {
                ItemChatMeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            }
            MESSAGE_RECEIVED ->{
                ItemChatOtherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            }
            else -> {
                ItemChatMeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            }
        }
        return  ChatViewHolder(binding)
    }

    override fun getItemCount(): Int = messageList.size

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(messageList[position],getItemViewType(position) )
    }

    // para seleccionar que vista pintar
    override fun getItemViewType(position: Int): Int {
        return if (messageList[position].user.username == userName) {
            MESSAGE_SENT
        } else {
            MESSAGE_RECEIVED
        }
    }

    fun updateList(it: MutableList<MessageModel>, name:String) {
        userName = name
        messageList.clear()
        messageList.addAll(it)
        //notificar que se inserto un nuevo item
        // -1 para que se inserte en la ultima posicion
        notifyItemInserted(messageList.size -1)

    }

}