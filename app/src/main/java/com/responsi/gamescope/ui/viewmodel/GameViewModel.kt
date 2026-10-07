package com.responsi.gamescope.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.responsi.gamescope.data.model.GameDetailResponse
import com.responsi.gamescope.data.model.GameItem
import com.responsi.gamescope.data.repository.GameRepository
import com.responsi.gamescope.data.repository.GameRepositoryImpl
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface GameListUiState {
    object Loading : GameListUiState
    data class Success(val games: List<GameItem>) : GameListUiState
    data class Error(val message: String) : GameListUiState
}

sealed interface GameDetailUiState {
    object Idle : GameDetailUiState
    object Loading : GameDetailUiState
    data class Success(val game: GameDetailResponse) : GameDetailUiState
    data class Error(val message: String) : GameDetailUiState
}

class GameViewModel(
    private val repository: GameRepository = GameRepositoryImpl()
) : ViewModel() {

    private val _gamesUiState = MutableStateFlow<GameListUiState>(GameListUiState.Loading)
    val gamesUiState: StateFlow<GameListUiState> = _gamesUiState.asStateFlow()

    private val _detailUiState = MutableStateFlow<GameDetailUiState>(GameDetailUiState.Idle)
    val detailUiState: StateFlow<GameDetailUiState> = _detailUiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private var searchJob: Job? = null

    init {
        fetchGames()
    }

    fun fetchGames(query: String? = null) {
        viewModelScope.launch {
            _gamesUiState.value = GameListUiState.Loading
            val result = repository.getGames(search = query)
            result.onSuccess { games ->
                _gamesUiState.value = GameListUiState.Success(games)
            }.onFailure { error ->
                _gamesUiState.value = GameListUiState.Error(
                    error.localizedMessage ?: "Terjadi kesalahan saat memuat data game."
                )
            }
        }
    }

    fun onSearchQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500) // Debounce 500ms agar tidak spam API saat mengetik
            fetchGames(newQuery)
        }
    }

    fun clearSearch() {
        _searchQuery.value = ""
        fetchGames()
    }

    fun fetchGameDetail(gameId: Int) {
        viewModelScope.launch {
            _detailUiState.value = GameDetailUiState.Loading
            val result = repository.getGameDetail(gameId)
            result.onSuccess { gameDetail ->
                _detailUiState.value = GameDetailUiState.Success(gameDetail)
            }.onFailure { error ->
                _detailUiState.value = GameDetailUiState.Error(
                    error.localizedMessage ?: "Gagal memuat detail game."
                )
            }
        }
    }

    fun resetDetailState() {
        _detailUiState.value = GameDetailUiState.Idle
    }
}
