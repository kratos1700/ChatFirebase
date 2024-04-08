package com.example.secretxat.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.secretxat.domain.GetUsernameUseCase
import com.example.secretxat.domain.SaveUserNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val userNameUseCase: SaveUserNameUseCase,
                                        private val getUsernameUseCase: GetUsernameUseCase) :
    ViewModel() {

        init {
            verifyUserLogger()

        }

    private var _uiState = MutableStateFlow<MainViewState>(MainViewState.LOADING)
    val uiState: StateFlow<MainViewState> = _uiState

    fun saveNickName(nickName: String) {

        viewModelScope.launch(Dispatchers.IO) {
            userNameUseCase(nickName)

        }
    }

    private fun verifyUserLogger() {
        viewModelScope.launch {
            val name = async { getUsernameUseCase() }.await()  // espera a que es realitzi la tasca
            if (name.isNotEmpty()) {
                _uiState.value = MainViewState.REGISTERED
            } else {
                _uiState.value = MainViewState.UNREGISTERED
            }
        }

    }
}

sealed class MainViewState {
    object LOADING : MainViewState()
    object UNREGISTERED : MainViewState()
    object REGISTERED : MainViewState()
}