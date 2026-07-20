package com.example.rickandmortyjetpackcompose.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyjetpackcompose.domain.repository.CharacterRepository
import com.example.rickandmortyjetpackcompose.presentation.ui.MainUiState
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.SocketTimeoutException

class MainViewModel(private val repository: CharacterRepository) : ViewModel() {

    private var _myResponse: MutableLiveData<MainUiState> = MutableLiveData()
    val myResponse: LiveData<MainUiState> = _myResponse

    fun getCharacter() {
        viewModelScope.launch {
            _myResponse.value = MainUiState.Loading

            try {
                val response = repository.getCharacter()
                if (response.isSuccessful) {
                    val characters = response.body()?.charactersInfo
                    if (characters != null) {
                        _myResponse.value = MainUiState.Success(characters)
                    } else {
                        _myResponse.value = MainUiState.Error("No characters found")
                    }
                } else {
                    _myResponse.value = MainUiState.Error("Server error")
                }

            } catch (e: SocketTimeoutException) {
                _myResponse.value = MainUiState.Error("Erro na conexao: ${e.message}")
            } catch (e: IOException) {
                _myResponse.value = MainUiState.Error("Erro na conexao: ${e.message}")
            } catch (e: JsonSyntaxException) {
                _myResponse.value = MainUiState.Error("Erro na conversao de dados: ${e.message}")
            } catch (e: Exception) {
                _myResponse.value = MainUiState.Error("Erro inesperado: ${e.message}")
            }
        }
    }
}