package com.example.moviecatalog.presentation.screen.profilescreen

import androidx.lifecycle.ViewModel
import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.domain.state.ProfileState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel : ViewModel() {
    private val emptyState = ProfileState (
        nickName = Constants.EMPTY_STRING,
        email = Constants.EMPTY_STRING,
        avatarLink = null,
        gender = Constants.ZERO,
        date = Constants.EMPTY_STRING,
        birthday = Constants.EMPTY_STRING,
        isDatePickerOpened = Constants.FALSE,
        changesInProfile = Constants.FALSE
    )

    private val _state = MutableStateFlow(emptyState)
    val state: StateFlow<ProfileState> get() = _state

    fun processIntent(intent: ProfileIntent) {
        when (intent) {
            is ProfileIntent.UpdateEmail -> {
                _state.value = state.value.copy(email = intent.email)
            }
            is ProfileIntent.UpdateAvatarLink -> {
                _state.value = state.value.copy(avatarLink = intent.link)
            }
            is ProfileIntent.UpdateNickName -> {
                _state.value = state.value.copy(nickName = intent.name)
            }
            is ProfileIntent.UpdateGender -> {
                val newGender = if (state.value.gender == 0) 1 else 0
                _state.value = state.value.copy(gender = newGender)
            }
            is ProfileIntent.UpdateDate -> {
                _state.value = state.value.copy(date = intent.date)
            }

            ProfileIntent.UpdateDatePickerVisibility -> {
                _state.value = state.value.copy(
                    isDatePickerOpened = !_state.value.isDatePickerOpened
                )
            }
            is ProfileIntent.SaveData -> {

            }
            is ProfileIntent.Cancel -> {

            }
        }
    }
}




