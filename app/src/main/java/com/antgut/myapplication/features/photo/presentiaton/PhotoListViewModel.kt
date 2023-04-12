package com.antgut.myapplication.features.photo.presentiaton

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.features.photo.domain.GetPhotosByAlbumUseCase
import com.antgut.myapplication.features.photo.domain.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoListViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosByAlbumUseCase,
) : ViewModel() {

    private val _uiModel = MutableLiveData<UiModel>()
    val uiModel: LiveData<UiModel>
        get() = _uiModel

    fun loadPhotosByAlbum(albumId: Int) {
        _uiModel.value = UiModel(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            getPhotosUseCase.invoke(albumId).apply {
                this.map { listFlow ->
                    listFlow.collect {
                        _uiModel.postValue(
                            UiModel(
                                isLoading = false,
                                error = this.swap().getOrNull(),
                                photoList = it

                            )
                        )
                    }
                }
            }
        }
    }

    data class UiModel(
        val isLoading: Boolean = false,
        val error: ErrorApp? = null,
        val photoList: List<Photo>? = null
    )
}