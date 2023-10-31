package com.example.moviecatalog.presentation.screen.profilescreen

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.domain.state.ProfileState
import com.example.moviecatalog.domain.usecase.GetProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel() : ViewModel() {
    private val getProfileUseCase = GetProfileUseCase()

    private val emptyState = ProfileState (
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

    private val _state = MutableStateFlow(emptyState)
    val state: StateFlow<ProfileState> get() = _state

    init {
        performData()
    }

    fun processIntent(intent: ProfileIntent) {
        when (intent) {
            is ProfileIntent.UpdateNickName -> {
                _state.value = state.value.copy(nickName = intent.nickName)
            }
            is ProfileIntent.UpdateEmail -> {
                _state.value = state.value.copy(email = intent.email)
            }
            is ProfileIntent.UpdateAvatarLink -> {
                _state.value = state.value.copy(avatarLink = intent.link)
            }
            is ProfileIntent.UpdateName -> {
                _state.value = state.value.copy(name = intent.name)
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

            is ProfileIntent.UpdateEmailError -> {

            }
        }
    }

    fun isDatePickerOpen() : Boolean {
        return state.value.isDatePickerOpened
    }

    private fun performData(){
        viewModelScope.launch {
            try {
                val result = getProfileUseCase.invoke()
                if (result.isSuccess) {
                    val response = result.getOrNull()
                    if (response != null) {
                        Log.d("DebugProfile", response.toString())
                        processIntent(ProfileIntent.UpdateNickName(response.nickName))
                        processIntent(ProfileIntent.UpdateEmail(response.email))
                        processIntent(ProfileIntent.UpdateAvatarLink(response.avatarLink))
                        processIntent(ProfileIntent.UpdateName(response.name))
                        processIntent(ProfileIntent.UpdateDate(response.birthDate, response.birthDate))
                    }
                } else {
                    Log.d("Mem", "hahaha")
                }
            } catch (e: Exception) {
                Log.d("ERROR", e.message.toString())
            }
        }
    }
}




