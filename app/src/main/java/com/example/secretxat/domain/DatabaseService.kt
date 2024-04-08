package com.example.secretxat.domain

import kotlinx.coroutines.flow.Flow

interface DatabaseService {
    suspend fun saveNickName(nickName: String)
    suspend fun clear()
     fun getNickName(): Flow<String>
}