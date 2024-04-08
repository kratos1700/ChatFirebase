package com.example.secretxat.domain

import android.icu.util.Calendar
import com.example.secretxat.data.network.FirebaseChatService
import com.example.secretxat.data.network.dto.MessageDto
import com.example.secretxat.data.network.dto.UserDto
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(private val firebaseChatService: FirebaseChatService){

// al cas d'us va la logica del negoci

    operator fun invoke(message: String,nickName:String) {

        val calendar: Calendar = Calendar.getInstance()


        val hour : Int = calendar.get(Calendar.HOUR_OF_DAY)
        val min : Int = calendar.get(Calendar.MINUTE)
        val year :Int = calendar.get(Calendar.YEAR)
        val month :Int = calendar.get(Calendar.MONTH)+1
        val day :Int = calendar.get(Calendar.DAY_OF_MONTH)

        val userDto = UserDto(
            username = nickName,
            admin = false
        )

        val messageDto = MessageDto (
            msg= message,
            hour= "$hour:$min",
            date= "$day/$month/$year",
            user = userDto
        )

        firebaseChatService.sendMsgTOFirebase(messageDto)

    }
}