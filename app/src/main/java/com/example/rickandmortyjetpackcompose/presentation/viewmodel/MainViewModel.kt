package com.example.rickandmortyjetpackcompose.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyjetpackcompose.domain.repository.CharacterRepository
import com.example.rickandmortyjetpackcompose.presentation.state.MainUiState
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * ViewModel responsável por gerenciar os dados da tela principal.
 * Ele atua como uma ponte entre o repositório (Domain) e a UI (Presentation).
 */
class MainViewModel(private val repository: CharacterRepository) : ViewModel() {

    // LiveData interno (mutável) para controlar o estado da UI.
    private var _myResponse: MutableLiveData<MainUiState> = MutableLiveData()
    
    // LiveData externo (imutável) observado pela MainActivity.
    val myResponse: LiveData<MainUiState> = _myResponse

    /**
     * Inicia o processo de busca de personagens.
     * Altera o estado para Loading, realiza a chamada assíncrona e trata os resultados/erros.
     */
    fun getCharacter() {
        viewModelScope.launch {
            // Define o estado inicial como carregando.
            _myResponse.value = MainUiState.Loading

            try {
                // Chama o repositório para obter os dados.
                val response = repository.getCharacter()
                
                if (response.isSuccessful) {
                    val characters = response.body()?.charactersInfo
                    if (characters != null) {
                        // Se sucesso e dados presentes, atualiza para o estado Success.
                        _myResponse.value = MainUiState.Success(characters)
                    } else {
                        _myResponse.value = MainUiState.Error("Nenhum personagem encontrado")
                    }
                } else {
                    // Trata erros de resposta do servidor (ex: 404, 500).
                    _myResponse.value = MainUiState.Error("Erro no servidor: ${response.code()}")
                }

            } catch (e: SocketTimeoutException) {
                _myResponse.value = MainUiState.Error("Tempo de conexão esgotado: ${e.message}")
            } catch (e: IOException) {
                _myResponse.value = MainUiState.Error("Falha de rede: Verifique sua conexão")
            } catch (e: JsonSyntaxException) {
                _myResponse.value = MainUiState.Error("Erro ao processar dados da API")
            } catch (e: Exception) {
                _myResponse.value = MainUiState.Error("Erro inesperado: ${e.message}")
            }
        }
    }
}
