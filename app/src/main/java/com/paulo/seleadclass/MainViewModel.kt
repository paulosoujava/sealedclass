package com.paulo.seleadclass

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: MainRepository = MainRepository()
) : ViewModel() {


    private val _uiState = mutableStateOf(UiState())
    val uiState: State<UiState> = _uiState

    init {
        fetchData()
    }

    private fun fetchData(){

        viewModelScope.launch {
            _uiState.value = UiState(isLoading = true)
            when (val data = repository.getData()) {
                is Resource.Success -> {
                    _uiState.value = UiState(items = data.data ?: listOf())
                }
                is Resource.Error -> {
                    _uiState.value = UiState(error = UiState.Error.Network)
                }
            }
        }
    }

}

data class UiState(
    val isLoading: Boolean = false,
    val error: Error? = null,
    val items: List<Person> = listOf()
) {
    sealed class Error {
        object Network : Error()
        object InputTooShort : Error()
        object InputEmpty : Error()
    }
}