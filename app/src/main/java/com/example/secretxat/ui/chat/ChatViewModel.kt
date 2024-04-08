package com.example.secretxat.ui.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.secretxat.domain.GetMessageUseCase
import com.example.secretxat.domain.GetUsernameUseCase
import com.example.secretxat.domain.LogOutUseCase
import com.example.secretxat.domain.SendMessageUseCase
import com.example.secretxat.domain.model.MessageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val getMessageUseCase: GetMessageUseCase,
    private val getUserNameUseCase: GetUsernameUseCase,
    private val logOutUseCase: LogOutUseCase
) : ViewModel() {

    var name: String = " "

    private var _messageList = MutableStateFlow<List<MessageModel>>(emptyList())
    val messageList:StateFlow<List<MessageModel>> = _messageList


    // al iniciar el viewmodel cridem a getMessages
    init {
        getUserName()
        getMessages()
    }

    private fun getUserName() {
        viewModelScope.launch(Dispatchers.IO) {
            name = getUserNameUseCase()
        }
    }



    fun sendMessage(msg:String) {

        sendMessageUseCase(msg, name)

    }

    private fun getMessages() {
        viewModelScope.launch {
            getMessageUseCase().collect {
                Log.d("RESPONSE", "La info es: $it")
                _messageList.value = it
            }
        }
    }

    // creem una funcio lamda per a que quan es cridi a la funcio logout, es cridi a la funcio onViewFinish : () -> Unit
    fun disconnect(onViewFinish : () -> Unit){
        viewModelScope.launch {
            // async hace que la función se ejecute en un hilo secundario
            // await hace que la función espere a que se ejecute la función async
          async {   logOutUseCase() } .await()
            onViewFinish() // cridem a la funcio lamda
        }
    }

}