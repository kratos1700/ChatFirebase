package com.example.secretxat.data.network.dto

data class MessageDto(
    val msg: String,
    val hour: String,
    val date: String,
    val user: UserDto

)

data class UserDto(
    val username: String,
    val admin: Boolean
)

//msg
//hora
//data
//user ---- username, admin:boolean