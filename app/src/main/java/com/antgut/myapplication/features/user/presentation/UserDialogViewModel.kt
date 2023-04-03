package com.antgut.myapplication.features.user.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antgut.myapplication.app.domain.ErrorApp
import com.antgut.myapplication.features.user.domain.DeleteServerUserUseCase
import com.antgut.myapplication.features.user.domain.GetServerUserUseCase
import com.antgut.myapplication.features.user.domain.SaveServerUserUseCase
import com.antgut.myapplication.features.user.domain.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDialogViewModel @Inject constructor(
    private val saveServerUserUseCase: SaveServerUserUseCase,
    private val getServerUserUseCase: GetServerUserUseCase,
    private val deleteServerUserUseCase: DeleteServerUserUseCase
) : ViewModel() {
    private val _uiModel = MutableLiveData<UiModel>()
    val uiModel: LiveData<UiModel>
        get() = _uiModel

    fun saveUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            saveServerUserUseCase(user).apply {
                _uiModel.postValue(
                    UiModel(
                        isSuccess = false,
                        isSaved = true,
                        user = user
                    )
                )
            }
        }
    }

    fun getUser(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getServerUserUseCase.invoke(userId).apply {
                _uiModel.postValue(
                    UiModel(
                        isSuccess = false,
                        isSaved = false,
                        error = this.swap().getOrNull(),
                        user = this.getOrNull()
                    )
                )
            }
            getServerUserUseCase(userId).map { user ->
                _uiModel.postValue(UiModel(user = user))
            }
        }
    }

    fun deleteUser(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteServerUserUseCase(userId).fold(
                { errorResponse(it) },
                { successDeleteUser(it) }
            )
        }
    }

    private fun errorResponse(errorApp: ErrorApp) {
        _uiModel.postValue(UiModel(error = errorApp))
    }

    private fun successDeleteUser(isSuccess: Boolean) {
        _uiModel.postValue(UiModel(isSuccess = isSuccess))
    }

    data class UiModel(
        val user: User? = null,
        val isSuccess: Boolean = false,
        val error: ErrorApp? = null,
        val isSaved: Boolean = false
    )
}