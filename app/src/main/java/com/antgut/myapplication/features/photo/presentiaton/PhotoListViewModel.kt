package com.antgut.myapplication.features.photo.presentiaton

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.features.photo.domain.GetPhotosUseCase
import com.antgut.myapplication.features.photo.domain.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoListViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState>
        get() = _uiState

    fun loadPhotos(albumId: Int) {
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            getPhotosUseCase.invoke(albumId).apply {
                _uiState.postValue(
                    UiState(
                        isLoading = false,
                        error = this.swap().getOrNull(),
                        photoList = this.getOrNull()
                    )
                )
            }
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val error: ErrorApp? = null,
        val photoList: List<Photo>? = null
    )
}