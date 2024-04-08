package com.example.secretxat.data.network.response

import com.example.secretxat.domain.model.MessageModel
import com.example.secretxat.domain.model.UserModel


data class MessageResponse(
    val msg: String? = null,
    val hour: String? = null,
    val date: String? = null,
    val user: UserResponse? = null

){
    fun toDomain():MessageModel{
        return MessageModel(
            msg = msg.orEmpty(),
            hour = hour ?: "no date",
            date = date.orEmpty(),
            user = UserModel(username = user?.username ?:"Guess", admin = user?.admin ?: true)
        )
    }
}

data class UserResponse(
    val username: String? = null,
    val admin: Boolean? = null
)