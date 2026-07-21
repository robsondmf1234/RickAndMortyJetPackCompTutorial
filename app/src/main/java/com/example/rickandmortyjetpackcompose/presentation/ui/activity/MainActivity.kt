package com.example.rickandmortyjetpackcompose.presentation.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rickandmortyjetpackcompose.domain.model.CharacterDTO
import com.example.rickandmortyjetpackcompose.domain.model.CharacterMock
import com.example.rickandmortyjetpackcompose.presentation.state.MainUiState
import com.example.rickandmortyjetpackcompose.presentation.ui.theme.RickAndMortyJetPackComposeTheme
import com.example.rickandmortyjetpackcompose.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * MainActivity: Ponto de entrada da aplicação.
 * Responsável por configurar a UI e conectar o ViewModel com a tela utilizando Jetpack Compose.
 */
class MainActivity : ComponentActivity() {

    // Injeção de dependência do ViewModel utilizando Koin.
    // O Koin gerencia a criação da instância e suas dependências automaticamente.
    private val viewModel: MainViewModel by viewModel()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Habilita o design de borda a borda (edge-to-edge), permitindo que o app ocupe toda a tela.
        enableEdgeToEdge()

        // Dispara a busca de personagens no ViewModel assim que a Activity é criada.
        viewModel.getCharacter()

        setContent {
            // Aplica o tema base da aplicação definido no Material Design 3.
            RickAndMortyJetPackComposeTheme {

                // Observa o estado da UI vindo do ViewModel através de LiveData. 
                // O estado inicial é 'Loading'. O Compose recompõe a tela sempre que uiState muda.
                val uiState by viewModel.myResponse.collectAsState()

                // Scaffold fornece a estrutura básica do layout (TopBar, BottomBar, etc).
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
                    // Box serve como container para respeitar os paddings automáticos do Scaffold (como a altura da TopBar).
                    Box(modifier = Modifier.padding(innerPadding)) {
                        // Pattern Matching (when) para decidir qual tela renderizar com base no estado atual da UI.
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
 * Tela de Carregamento: Exibe um indicador de progresso circular centralizado.
 */
@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

/**
 * Tela de Erro: Exibe a mensagem de erro formatada para o usuário.
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
 * Lista de Personagens: Renderiza uma LazyColumn, que é uma lista otimizada
 * que carrega apenas os itens visíveis na tela (semelhante ao RecyclerView).
 */
@Composable
fun CharacterList(characters: List<CharacterDTO>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp) // Espaçamento extra no final da lista para melhor visualização.
    ) {
        // Define como cada item da lista (Character) deve ser renderizado.
        items(characters) { character ->
            CharacterItem(character)
        }
    }
}

/**
 * Item Individual da Lista: Representa um Card com a foto e informações básicas do personagem.
 */
@Composable
fun CharacterItem(character: CharacterDTO) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp) // Adiciona uma leve sombra ao card.
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // AsyncImage do Coil: Carrega a imagem da URL de forma assíncrona,
            // gerenciando cache e memória automaticamente.
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

@Preview(showSystemUi = true)
@Composable
private fun LoadingScreenPreview() {
    LoadingScreen()
}

@Preview(showSystemUi = true)
@Composable
private fun ErrorScreenPreview() {
    ErrorScreen(message = "Ocorreu um erro ao carregar os dados.")
}

@Preview(showSystemUi = true)
@Composable
private fun CharacterListPreview() {
    RickAndMortyJetPackComposeTheme {
        CharacterList(characters = CharacterMock.mockCharacterList)
    }
}
