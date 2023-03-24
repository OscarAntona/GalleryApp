package com.antgut.myapplication.features.user.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.features.album.domain.Album
import com.antgut.myapplication.features.album.domain.GetAlbumsUseCase
import com.antgut.myapplication.features.user.domain.GetUsersUseCase
import com.antgut.myapplication.features.user.domain.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState>
        get() = _uiState

    fun loadAlbums() {
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            getUsersUseCase.invoke().apply {
                _uiState.postValue(
                    UiState(
                        isLoading = false,
                        error = this.swap().getOrNull(),
                        userList = this.getOrNull()
                    )
                )
            }
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val error: ErrorApp? = null,
        val userList: List<User>? = null
    )
}