package com.antgut.myapplication.features.album.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.features.album.domain.Album
import com.antgut.myapplication.features.album.domain.SaveAlbumUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumAddDialogViewModel @Inject constructor(
    private val saveAlbumUseCase: SaveAlbumUseCase
) : ViewModel() {
    private val _uiModel = MutableLiveData<UiModel>()
    val uiModel: LiveData<UiModel>
        get() = _uiModel

    fun saveAlbum(album: Album) {
        viewModelScope.launch(Dispatchers.IO) {
            saveAlbumUseCase(album).apply {
                _uiModel.postValue(
                    UiModel(
                        isSaved = true,
                        album = album
                    )
                )
            }
        }
    }

    data class UiModel(
        val album: Album? = null,
        val error: ErrorApp? = null,
        val isSaved: Boolean = false
    )
}