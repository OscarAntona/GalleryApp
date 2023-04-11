package com.antgut.myapplication.features.user.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.features.user.domain.SaveUserUseCase
import com.antgut.myapplication.features.user.domain.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserAddDialogViewModel @Inject constructor(
    private val saveUserUseCase: SaveUserUseCase
) : ViewModel() {
    private val _uiModel = MutableLiveData<UiModel>()
    val uiModel: LiveData<UiModel>
        get() = _uiModel

    fun saveUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            saveUserUseCase(user).apply {
                _uiModel.postValue(
                    UiModel(
                        isSaved = true,
                        user = user
                    )
                )
            }
        }
    }

    data class UiModel(
        val user: User? = null,
        val error: ErrorApp? = null,
        val isSaved: Boolean = false
    )
}