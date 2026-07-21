package com.example.rickandmortyjetpackcompose.presentation.state

import com.example.rickandmortyjetpackcompose.domain.model.Character

/**
 * Representa os diferentes estados da Interface do Usuário (UI) na tela principal.
 */
sealed class MainUiState {
    /** Estado de carregamento inicial. */
    object Loading : MainUiState()
    
    /** Estado de sucesso, contendo a lista de personagens carregada. */
    data class Success(val characters: List<Character>) : MainUiState()

    /** Estado de erro, contendo uma mensagem descritiva. */
    data class Error(val message: String) : MainUiState()
}
