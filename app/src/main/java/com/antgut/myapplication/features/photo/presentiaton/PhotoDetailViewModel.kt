package com.antgut.myapplication.features.photo.presentiaton

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.features.photo.domain.GetPhotoUseCase
import com.antgut.myapplication.features.photo.domain.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoDetailViewModel @Inject constructor(
    private val getPhotoUseCase: GetPhotoUseCase
) : ViewModel() {
    private val _uiModel = MutableLiveData<UiModel>()
    val uiModel: LiveData<UiModel>
        get() = _uiModel

    fun loadPhoto(photoId: Int) {
        _uiModel.value = UiModel(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            getPhotoUseCase.invoke(photoId).apply {
                _uiModel.postValue(
                    UiModel(
                        isLoading = false,
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

    data class UiModel(
        val isLoading: Boolean = false,
        val photo: Photo? = null,
        val error: ErrorApp? = null,
    )
}