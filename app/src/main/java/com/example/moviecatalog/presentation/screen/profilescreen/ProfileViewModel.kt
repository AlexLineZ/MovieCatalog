package com.example.moviecatalog.presentation.screen.profilescreen

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.common.Formatter.formatDateToNormal
import com.example.moviecatalog.data.network.NetworkService
import com.example.moviecatalog.domain.model.profile.Profile
import com.example.moviecatalog.domain.state.ProfileState
import com.example.moviecatalog.domain.usecase.DataValidateUseCase
import com.example.moviecatalog.domain.usecase.DeleteTokenUseCase
import com.example.moviecatalog.domain.usecase.GetProfileUseCase
import com.example.moviecatalog.domain.usecase.PostLogoutUseCase
import com.example.moviecatalog.domain.usecase.PutProfileDataUseCase
import com.example.moviecatalog.domain.validator.EmailValidator
import com.example.moviecatalog.domain.validator.NameValidator
import com.example.moviecatalog.presentation.router.LogoutRouter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    val context: Context,
    val router: LogoutRouter
) : ViewModel() {
    private val getProfileUseCase = GetProfileUseCase()
    private val putProfileDataUseCase = PutProfileDataUseCase()
    private val dataValidateUseCase = DataValidateUseCase()
    private val postLogoutUseCase = PostLogoutUseCase()
    private val deleteTokenUseCase = DeleteTokenUseCase(context)

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
        nameError = null,
        isDatePickerOpened = Constants.FALSE,
        changesInProfile = Constants.FALSE,
        isLoading = Constants.FALSE
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
                _state.value = state.value.copy(email = intent.email.trim())
                processIntent(
                    ProfileIntent.UpdateEmailError(
                        dataValidateUseCase.invoke(EmailValidator(), intent.email)
                    )
                )
                processIntent(ProfileIntent.UpdateChanges(isChange = true))
            }
            is ProfileIntent.UpdateAvatarLink -> {
                _state.value = state.value.copy(avatarLink = intent.link?.trim())
                processIntent(ProfileIntent.UpdateChanges(isChange = true))
            }
            is ProfileIntent.UpdateName -> {
                _state.value = state.value.copy(name = intent.name)
                processIntent(
                    ProfileIntent.UpdateNameError(
                        dataValidateUseCase.invoke(NameValidator(), intent.name)
                    )
                )
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
            is ProfileIntent.UpdateNameError -> {
                _state.value = state.value.copy(nameError = intent.error)
            }
            is ProfileIntent.SaveData -> {
                processIntent(ProfileIntent.UpdateName(state.value.name.trim()))
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

            ProfileIntent.UpdateLoading -> {
                _state.value = state.value.copy(
                    isLoading = !_state.value.isLoading
                )
            }
        }
    }

    private fun rollbackToInitialState() {
        _state.value = initialProfileStateFlow.value
        _state.value.isLoading = Constants.FALSE
    }

    fun isDatePickerOpen() : Boolean {
        return state.value.isDatePickerOpened
    }

    fun isSaveButtonAvailable(): Boolean {
        return _state.value.changesInProfile
                && _state.value.emailError == null
                && _state.value.nameError == null
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
            } else if (result.isFailure){
                userOutLogin { router.toErrorAfterOut() }
            }
        }
    }

    fun refreshData() {
        processIntent(ProfileIntent.UpdateLoading)
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
            } else if (result.isFailure){
                userOutLogin { router.toErrorAfterOut() }
            }
            delay(500)
            processIntent(ProfileIntent.UpdateLoading)
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
            try {
                val result = postLogoutUseCase.invoke()
                if (result.isSuccess) {
                    deleteTokenUseCase.invoke()
                    toAfterLogout()
                } else {
                    userOutLogin { router.toErrorAfterOut() }
                }
            } catch (e: Exception) {
                userOutLogin { router.toErrorAfterOut() }
            }
        }
    }

    private fun userOutLogin(goTo: () -> Unit){
        NetworkService.setAuthToken(Constants.EMPTY_STRING)
        goTo()
    }
}




