package com.antgut.myapplication.features.album.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.features.album.domain.Album
import com.antgut.myapplication.features.album.domain.GetAlbumsByUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(
    private val getAlbumsUseCase: GetAlbumsByUserUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState>
        get() = _uiState

    fun loadAlbums(userId: Int) {
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            getAlbumsUseCase.invoke(userId).apply {
                _uiState.postValue(
                    UiState(
                        isLoading = false,
                        error = this.swap().getOrNull(),
                        albumList = this.getOrNull()
                    )
                )
            }
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val error: ErrorApp? = null,
        val albumList: List<Album>? = null
    )
}