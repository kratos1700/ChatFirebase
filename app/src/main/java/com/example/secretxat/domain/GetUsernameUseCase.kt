package com.example.secretxat.domain

import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetUsernameUseCase @Inject constructor(private val databaseService: DatabaseService) {

    //esta función se encarga de obtener el nombre de usuario
    // .first() es una función de extensión de Flow que devuelve el primer valor emitido por el flujo.
    suspend operator fun invoke() = databaseService.getNickName().first()

}