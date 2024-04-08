package com.example.secretxat.domain

import com.example.secretxat.data.network.FirebaseChatService
import javax.inject.Inject

class GetMessageUseCase @Inject constructor(private val firebaseChatService: FirebaseChatService){
    operator fun invoke() =  firebaseChatService.getMessages()

}