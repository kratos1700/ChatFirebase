package com.example.secretxat.ui.chat.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.secretxat.databinding.ItemChatMeBinding
import com.example.secretxat.databinding.ItemChatOtherBinding
import com.example.secretxat.domain.model.MessageModel

class ChatViewHolder(private val binding : ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(messageModel: MessageModel, itemViewType: Int) {
        when(itemViewType){
            ChatAdapter.MESSAGE_SENT -> bindingSendMessge(messageModel)

            ChatAdapter.MESSAGE_RECEIVED -> bindingReceiveMessage(messageModel)
        }
    }

    private fun bindingReceiveMessage(messageModel: MessageModel) {
        val currentbinding = binding as ItemChatOtherBinding

        currentbinding.tvDateMe.text = messageModel.date
        currentbinding.tvMessageMe.text = messageModel.msg
        currentbinding.tvName.text = messageModel.user.username
        currentbinding.tvHour.text = messageModel.hour

    }

    private fun bindingSendMessge(messageModel: MessageModel) {
        val currentbinding = binding as ItemChatMeBinding

        currentbinding.tvDateMe.text = messageModel.date
        currentbinding.tvMessageMe.text = messageModel.msg
        currentbinding.tvHour.text = messageModel.hour


    }
}