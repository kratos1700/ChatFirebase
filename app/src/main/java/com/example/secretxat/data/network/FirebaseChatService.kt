package com.example.secretxat.data.network

import com.example.secretxat.data.network.dto.MessageDto
import com.example.secretxat.data.network.response.MessageResponse
import com.example.secretxat.domain.model.MessageModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FirebaseChatService @Inject constructor(private val reference:DatabaseReference){

    companion object {
        private const val PATH = "messages"
    }

    fun sendMsgTOFirebase(messageDto: MessageDto){

        val newMsg = reference.child(PATH).push()
        newMsg.setValue(messageDto)

    }

    fun getMessages():Flow<List<MessageModel>>{

        return reference.child(PATH).snapshots.map {  dataSnapshot ->
            //recorre tots el fills de la base de dades i els mapeja a MessageResponse
            dataSnapshot.children.mapNotNull {
                it.getValue(MessageResponse::class.java)?.toDomain()
            }
        }
    }


}