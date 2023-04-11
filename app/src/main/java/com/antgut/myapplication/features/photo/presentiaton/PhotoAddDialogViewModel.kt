package com.antgut.myapplication.features.photo.presentiaton

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.features.photo.domain.Photo
import com.antgut.myapplication.features.photo.domain.SavePhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoAddDialogViewModel @Inject constructor(
    private val savePhotoUseCase: SavePhotoUseCase
) : ViewModel() {
    private val _uiModel = MutableLiveData<UiModel>()
    val uiModel: LiveData<UiModel>
        get() = _uiModel

    fun savePhoto(photo: Photo) {
        viewModelScope.launch(Dispatchers.IO) {
            savePhotoUseCase(photo).apply {
                _uiModel.postValue(
                    UiModel(
                        isSaved = true,
                        photo = photo
                    )
                )
            }
        }
    }

    data class UiModel(
        val photo: Photo? = null,
        val error: ErrorApp? = null,
        val isSaved: Boolean = false
    )
}