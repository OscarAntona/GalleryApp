package com.antgut.myapplication.features.album.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.features.album.domain.Album
import com.antgut.myapplication.features.album.domain.DeleteAlbumUseCase
import com.antgut.myapplication.features.album.domain.GetAlbumUseCase
import com.antgut.myapplication.features.album.domain.SaveAlbumUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumDialogViewModel @Inject constructor(
    private val saveAlbumUseCase: SaveAlbumUseCase,
    private val getAlbumUseCase: GetAlbumUseCase,
    private val deleteAlbumUseCase: DeleteAlbumUseCase
) : ViewModel() {
    private val _uiModel = MutableLiveData<UiModel>()
    val uiModel: LiveData<UiModel>
        get() = _uiModel

    fun saveAlbum(album: Album) {
        viewModelScope.launch(Dispatchers.IO) {
            viewModelScope.launch {
                saveAlbumUseCase(album)
                successSaveAlbum(true)
            }
        }
    }

    fun getAlbum(albumId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getAlbumUseCase(albumId).fold(
                { errorResponse(it) },
                { successGetAlbum(it) }
            )
            getAlbumUseCase(albumId).map { album ->
                _uiModel.postValue(UiModel(album = album))
            }
        }
    }

    fun deleteAlbum(albumId: Int) {
        viewModelScope.launch(Dispatchers.IO){
            deleteAlbumUseCase(albumId).fold(
                {errorResponse(it)},
                {successDeleteAlbum(it)}
            )
        }
    }
    private fun errorResponse(errorApp: ErrorApp) {
        _uiModel.postValue(UiModel(error = errorApp))
    }

    private fun successSaveAlbum(isSaved: Boolean) {
        _uiModel.postValue(UiModel(isSaved = isSaved))
    }

    private fun successDeleteAlbum(isSuccess: Boolean) {
        _uiModel.postValue(UiModel(isSuccess = isSuccess))
    }

    private fun successGetAlbum(album: Album) {
        _uiModel.postValue(UiModel(album = album))
    }

    data class UiModel(
        val album: Album? = null,
        val isSuccess: Boolean = false,
        val error: ErrorApp? = null,
        val isSaved: Boolean = false
    )
}