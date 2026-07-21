package com.example.rickandmortyjetpackcompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyjetpackcompose.domain.repository.CharacterRepository
import com.example.rickandmortyjetpackcompose.presentation.state.MainUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * ViewModel responsável por gerenciar os dados da tela principal.
 * Ele atua como uma ponte entre o repositório (Domain) e a UI (Presentation).
 * Não conhece detalhes de Retrofit, DTOs ou serialização.
 */
class MainViewModel(private val repository: CharacterRepository) : ViewModel() {

    private var _myResponse: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState.Loading)
    val myResponse: StateFlow<MainUiState> = _myResponse

    /**
     * Inicia o processo de busca de personagens.
     * Altera o estado para Loading, realiza a chamada assíncrona e trata os resultados/erros.
     */
    fun getCharacter() {
        viewModelScope.launch {
            _myResponse.value = MainUiState.Loading

            try {
                val characters = repository.getCharacter()

                if (characters.isNotEmpty()) {
                    _myResponse.value = MainUiState.Success(characters)
                } else {
                    _myResponse.value = MainUiState.Error("Nenhum personagem encontrado")
                }

            } catch (e: SocketTimeoutException) {
                _myResponse.value = MainUiState.Error("Tempo de conexão esgotado: ${e.message}")
            } catch (e: IOException) {
                _myResponse.value = MainUiState.Error("Falha de rede: Verifique sua conexão")
            } catch (e: Exception) {
                _myResponse.value = MainUiState.Error("Erro inesperado: ${e.message}")
            }
        }
    }
}
