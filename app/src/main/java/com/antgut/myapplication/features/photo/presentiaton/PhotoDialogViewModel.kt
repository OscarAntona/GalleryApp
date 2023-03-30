package com.antgut.myapplication.features.photo.presentiaton

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.features.photo.domain.DeletePhotoUseCase
import com.antgut.myapplication.features.photo.domain.GetPhotoUseCase
import com.antgut.myapplication.features.photo.domain.Photo
import com.antgut.myapplication.features.photo.domain.SavePhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoDialogViewModel @Inject constructor(
    private val getPhotoUseCase: GetPhotoUseCase,
    private val savePhotoUseCase: SavePhotoUseCase,
    private val deletePhotoUseCase: DeletePhotoUseCase
) : ViewModel() {
    private val _uiModel = MutableLiveData<UiModel>()
    val uiModel: LiveData<UiModel>
        get() = _uiModel

    fun savePhoto(photo: Photo) {
        viewModelScope.launch(Dispatchers.IO) {
            savePhotoUseCase(photo).apply {
                _uiModel.postValue(
                    UiModel(
                        isSuccess = false,
                        isSaved = true,
                        photo = photo
                    )
                )
            }
        }
    }

    fun getPhoto(photoId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getPhotoUseCase.invoke(photoId).apply {
                _uiModel.postValue(
                    UiModel(
                        isSuccess = false,
                        isSaved = false,
                        error = this.swap().getOrNull(),
                        photo = this.getOrNull()
                    )
                )
            }
            getPhotoUseCase(photoId).map { photo ->
                _uiModel.postValue(UiModel(photo = photo))
            }
        }
    }

    fun deletePhoto(photoId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            deletePhotoUseCase(photoId).fold(
                { errorResponse(it) },
                { successDeletePhoto(it) }
            )
        }
    }

    private fun errorResponse(errorApp: ErrorApp) {
        _uiModel.postValue(UiModel(error = errorApp))
    }

    private fun successDeletePhoto(isSuccess: Boolean) {
        _uiModel.postValue(UiModel(isSuccess = isSuccess))
    }

    data class UiModel(
        val error: ErrorApp? = null,
        val isSuccess: Boolean = false,
        val isSaved: Boolean = false,
        val photo: Photo? = null
    )
}