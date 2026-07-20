package com.example.rickandmortyjetpackcompose.presentation.state

import com.example.rickandmortyjetpackcompose.domain.model.Character

sealed class MainUiState {
    object Loading : MainUiState()
    data class Success(val characters: List<Character>) : MainUiState()
    data class Error(val message: String) : MainUiState()
}