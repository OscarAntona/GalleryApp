package com.antgut.myapplication.features.album.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.features.album.domain.Album
import com.antgut.myapplication.features.album.domain.GetAllAlbumsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val getAllAlbumsUseCase: GetAllAlbumsUseCase
) : ViewModel() {

    private val _uiModel = MutableLiveData<UiModel>()
    val uiModel: LiveData<UiModel>
        get() = _uiModel

    fun loadAlbums() {
        _uiModel.value = UiModel(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            getAllAlbumsUseCase.invoke().apply {
                _uiModel.postValue(
                    UiModel(
                        isLoading = false,
                        error = this.swap().getOrNull(),
                        albumList = this.getOrNull()
                    )
                )
            }
        }
    }

    data class UiModel(
        val isLoading: Boolean = false,
        val error: ErrorApp? = null,
        val albumList: List<Album>? = null
    )
}

