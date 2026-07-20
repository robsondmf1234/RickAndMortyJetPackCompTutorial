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

/**
 * MainActivity: Ponto de entrada da aplicação.
 * Responsável por configurar a UI e conectar o ViewModel com a tela.
 */
class MainActivity : ComponentActivity() {

    // Inicialização do ViewModel utilizando uma Factory manual.
    // Como ainda não estamos usando Injeção de Dependência (Dagger/Hilt ou Koin),
    // precisamos passar as dependências (Repository) manualmente.
    private val viewModel: MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                // Instancia o repositório passando a API configurada pelo Retrofit
                val repository = CharacterRepositoryImpl(RetrofitInstance.api)
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(repository) as T
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Habilita o design de borda a borda (edge-to-edge)
        enableEdgeToEdge()

        // Dispara a busca de personagens no ViewModel assim que a Activity é criada
        viewModel.getCharacter()

        setContent {
            RickAndMortyJetPackComposeTheme {
                // Observa o estado da UI vindo do ViewModel. 
                // O valor padrão inicial é 'Loading'.
                val uiState by viewModel.myResponse.observeAsState(MainUiState.Loading)

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Rick & Morty Characters") },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary
                            )
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    // Box serve como container para respeitar o padding do Scaffold
                    Box(modifier = Modifier.padding(innerPadding)) {
                        // Pattern Matching (when) para decidir qual tela renderizar com base no estado
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

/**
 * Tela de Carregamento: Exibe um indicador circular no centro.
 */
@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

/**
 * Tela de Erro: Exibe a mensagem de erro formatada.
 */
@Composable
fun ErrorScreen(message: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = message, 
            color = MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(16.dp)
        )
    }
}

/**
 * Lista de Personagens: Renderiza uma LazyColumn (semelhante ao RecyclerView).
 */
@Composable
fun CharacterList(characters: List<Character>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp) // Espaçamento extra no final da lista
    ) {
        items(characters) { character ->
            CharacterItem(character)
        }
    }
}

/**
 * Item Individual da Lista: Representa um Card com a foto e informações do personagem.
 */
@Composable
fun CharacterItem(character: Character) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp), 
            verticalAlignment = Alignment.CenterVertically
        ) {
            // AsyncImage do Coil: Carrega a imagem da URL de forma assíncrona
            AsyncImage(
                model = character.image,
                contentDescription = "Imagem de ${character.name}",
                modifier = Modifier
                    .size(80.dp)
                    .padding(4.dp)
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column {
                Text(
                    text = character.name, 
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "${character.species} - ${character.status}", 
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
