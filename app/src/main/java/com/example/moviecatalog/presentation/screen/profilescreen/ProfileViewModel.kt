package com.example.moviecatalog.presentation.screen.profilescreen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.common.formatDateToNormal
import com.example.moviecatalog.domain.model.profile.Profile
import com.example.moviecatalog.domain.state.ProfileState
import com.example.moviecatalog.domain.usecase.DataValidateUseCase
import com.example.moviecatalog.domain.usecase.DeleteTokenFromLocalStorageUseCase
import com.example.moviecatalog.domain.usecase.GetProfileUseCase
import com.example.moviecatalog.domain.usecase.PostLogoutUseCase
import com.example.moviecatalog.domain.usecase.PutProfileDataUseCase
import com.example.moviecatalog.domain.validator.EmailValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(val context: Context) : ViewModel() {
    private val getProfileUseCase = GetProfileUseCase()
    private val putProfileDataUseCase = PutProfileDataUseCase()
    private val dataValidateUseCase = DataValidateUseCase()
    private val postLogoutUseCase = PostLogoutUseCase()
    private val deleteTokenFromLocalStorageUseCase = DeleteTokenFromLocalStorageUseCase(context)

    private val emptyState = ProfileState (
        id = Constants.EMPTY_STRING,
        nickName = Constants.EMPTY_STRING,
        email = Constants.EMPTY_STRING,
        avatarLink = null,
        gender = Constants.ZERO,
        name = Constants.EMPTY_STRING,
        date = Constants.EMPTY_STRING,
        birthday = Constants.EMPTY_STRING,
        emailError = null,
        isDatePickerOpened = Constants.FALSE,
        changesInProfile = Constants.FALSE
    )

    private val initialProfileStateFlow = MutableStateFlow(emptyState)

    private val _state = MutableStateFlow(emptyState)
    val state: StateFlow<ProfileState> get() = _state

    fun processIntent(intent: ProfileIntent) {
        when (intent) {
            is ProfileIntent.ChangeId -> {
                _state.value = state.value.copy(id = intent.id)
            }
            is ProfileIntent.UpdateNickName -> {
                _state.value = state.value.copy(nickName = intent.nickName)
            }
            is ProfileIntent.UpdateEmail -> {
                _state.value = state.value.copy(email = intent.email)
                processIntent(
                    ProfileIntent.UpdateEmailError(
                        dataValidateUseCase.invoke(EmailValidator(), intent.email)
                    )
                )
                processIntent(ProfileIntent.UpdateChanges(isChange = true))
            }
            is ProfileIntent.UpdateAvatarLink -> {
                _state.value = state.value.copy(avatarLink = intent.link)
                processIntent(ProfileIntent.UpdateChanges(isChange = true))
            }
            is ProfileIntent.UpdateName -> {
                _state.value = state.value.copy(name = intent.name)
                processIntent(ProfileIntent.UpdateChanges(isChange = true))
            }
            is ProfileIntent.UpdateGender -> {
                _state.value = state.value.copy(gender = intent.gender)
                processIntent(ProfileIntent.UpdateChanges(isChange = true))
            }
            is ProfileIntent.UpdateDate -> {
                _state.value = state.value.copy(date = intent.date, birthday = intent.birthday)
                processIntent(ProfileIntent.UpdateChanges(isChange = true))
            }
            ProfileIntent.UpdateDatePickerVisibility -> {
                _state.value = state.value.copy(
                    isDatePickerOpened = !_state.value.isDatePickerOpened
                )
            }
            is ProfileIntent.UpdateEmailError -> {
                _state.value = state.value.copy(emailError = intent.error)
            }
            is ProfileIntent.SaveData -> {
                changeData()
            }
            is ProfileIntent.Cancel -> {
                rollbackToInitialState()
            }
            is ProfileIntent.UpdateChanges -> {
                _state.value = state.value.copy(changesInProfile = intent.isChange)
            }
            is ProfileIntent.Logout -> {
                logoutUser { intent.toAfterLogout() }
            }
        }
    }

    private fun rollbackToInitialState() {
        _state.value = initialProfileStateFlow.value
    }

    fun isDatePickerOpen() : Boolean {
        return state.value.isDatePickerOpened
    }

    fun isSaveButtonAvailable(): Boolean {
        return _state.value.changesInProfile
                && _state.value.emailError == null
                && _state.value.email.isNotEmpty()
                && _state.value.name.isNotEmpty()
                && _state.value.date.isNotEmpty()
    }

    fun performData() {
        viewModelScope.launch {
            val result = getProfileUseCase.invoke()
            if (result.isSuccess) {
                val response = result.getOrNull()
                if (response != null) {
                    processIntent(ProfileIntent.ChangeId(response.id))
                    processIntent(ProfileIntent.UpdateNickName(response.nickName))
                    processIntent(ProfileIntent.UpdateEmail(response.email))
                    processIntent(ProfileIntent.UpdateGender(response.gender))
                    processIntent(ProfileIntent.UpdateAvatarLink(response.avatarLink))
                    processIntent(ProfileIntent.UpdateName(response.name))
                    processIntent(
                        ProfileIntent.UpdateDate(
                            formatDateToNormal(response.birthDate),
                            response.birthDate
                        )
                    )
                    processIntent(ProfileIntent.UpdateChanges(isChange = false))
                    initialProfileStateFlow.value = _state.value
                }
            }
        }
    }

    private fun changeData() {
        val newData = Profile(
            id = state.value.id,
            nickName = state.value.nickName,
            email = state.value.email,
            avatarLink = state.value.avatarLink,
            name = state.value.name,
            birthDate = state.value.birthday,
            gender = state.value.gender
        )

        viewModelScope.launch {
            val result = putProfileDataUseCase.invoke(newData)
            if (result.isSuccess) {
                Toast.makeText(
                    context,
                    "Данные успешно изменены",
                    Toast.LENGTH_SHORT
                ).show()
                processIntent(ProfileIntent.UpdateChanges(isChange = false))
                initialProfileStateFlow.value = _state.value
            } else {
                Toast.makeText(
                    context,
                    "Произошла ошибка",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun logoutUser(toAfterLogout: () -> Unit) {
        viewModelScope.launch {
            val result = postLogoutUseCase.invoke()
            if (result.isSuccess) {
                deleteTokenFromLocalStorageUseCase.invoke()
                toAfterLogout()
            }
        }
    }
}




