package com.example.rickandmortyjetpackcompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyjetpackcompose.domain.usecase.GetCharactersUseCase
import com.example.rickandmortyjetpackcompose.presentation.state.MainUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * ViewModel responsável por gerenciar os dados da tela principal.
 * Delega a busca de personagens ao [GetCharactersUseCase] e converte
 * o resultado em estados de UI para a [presentation].
 */
class MainViewModel(private val getCharactersUseCase: GetCharactersUseCase) : ViewModel() {

    private var _uiState: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState.Loading)
    val uiState: StateFlow<MainUiState> = _uiState

    /**
     * Inicia a busca de personagens via UseCase.
     * Transforma o resultado em estado de UI (Loading, Success ou Error).
     */
    fun getCharacters() {
        viewModelScope.launch {
            _uiState.value = MainUiState.Loading

            try {
                val characters = getCharactersUseCase()

                if (characters.isNotEmpty()) {
                    _uiState.value = MainUiState.Success(characters)
                } else {
                    _uiState.value = MainUiState.Error("Nenhum personagem encontrado")
                }

            } catch (e: SocketTimeoutException) {
                _uiState.value = MainUiState.Error("Tempo de conexão esgotado: ${e.message}")
            } catch (e: IOException) {
                _uiState.value = MainUiState.Error("Falha de rede: Verifique sua conexão")
            } catch (e: Exception) {
                _uiState.value = MainUiState.Error("Erro inesperado: ${e.message}")
            }
        }
    }
}
