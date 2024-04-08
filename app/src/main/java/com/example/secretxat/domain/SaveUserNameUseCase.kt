package com.example.secretxat.domain

import javax.inject.Inject

class SaveUserNameUseCase @Inject constructor(private val databaseService: DatabaseService){
    suspend operator fun invoke(userName: String) {
        // Save the user name
        databaseService.saveNickName(userName)
    }
}