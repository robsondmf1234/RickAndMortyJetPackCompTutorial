package com.example.rickandmortyjetpackcompose.presentation.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImage
import com.example.rickandmortyjetpackcompose.data.api.RetrofitInstance
import com.example.rickandmortyjetpackcompose.data.repository.CharacterRepositoryImpl
import com.example.rickandmortyjetpackcompose.domain.model.Character
import com.example.rickandmortyjetpackcompose.presentation.ui.MainUiState
import com.example.rickandmortyjetpackcompose.presentation.ui.theme.RickAndMortyJetPackComposeTheme
import com.example.rickandmortyjetpackcompose.presentation.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    // Instanciando o ViewModel com uma Factory manual (enquanto não usa Hilt/Koin)
    private val viewModel: MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val repository = CharacterRepositoryImpl(RetrofitInstance.api)
                return MainViewModel(repository) as T
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inicia a busca de dados
        viewModel.getCharacter()

        setContent {
            RickAndMortyJetPackComposeTheme {
                val uiState by viewModel.myResponse.observeAsState(MainUiState.Loading)

                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text("Rick & Morty Characters") })
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        when (val state = uiState) {
                            is MainUiState.Loading -> LoadingScreen()
                            is MainUiState.Success -> CharacterList(state.characters)
                            is MainUiState.Error -> ErrorScreen(state.message)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(message: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = message, color = MaterialTheme.colorScheme.error)
    }
}

@Composable
fun CharacterList(characters: List<Character>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(characters) { character ->
            CharacterItem(character)
        }
    }
}

@Composable
fun CharacterItem(character: Character) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier
                    .size(80.dp)
                    .padding(4.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = character.name, style = MaterialTheme.typography.titleLarge)
                Text(text = "${character.species} - ${character.status}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
