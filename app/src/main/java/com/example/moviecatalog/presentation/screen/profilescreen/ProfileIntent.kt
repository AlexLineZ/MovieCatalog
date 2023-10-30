package com.example.moviecatalog.presentation.screen.profilescreen

import com.example.moviecatalog.presentation.screen.registrationscreen.RegistrationIntent

sealed class ProfileIntent{
    data class UpdateEmail(val email: String): ProfileIntent()
    data class UpdateAvatarLink(val link: String): ProfileIntent()
    data class UpdateNickName(val name: String): ProfileIntent()
    object UpdateGender: ProfileIntent()
    data class UpdateDate(val date: String, val birthday: String): ProfileIntent()
    object UpdateDatePickerVisibility : ProfileIntent()

    object SaveData: ProfileIntent()
    object Cancel: ProfileIntent()
}
