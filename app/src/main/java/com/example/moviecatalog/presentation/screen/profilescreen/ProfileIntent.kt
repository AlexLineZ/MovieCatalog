package com.example.moviecatalog.presentation.screen.profilescreen

sealed class ProfileIntent{
    data class UpdateEmail(val email: String): ProfileIntent()
    data class UpdateAvatarLink(val link: String): ProfileIntent()
    data class UpdateNickName(val name: String): ProfileIntent()
    object UpdateGender: ProfileIntent()
    data class UpdateDate(val date: String, val birthday: String): ProfileIntent()
    object UpdateDatePickerVisibility : ProfileIntent()

    data class UpdateEmailError(val error: String?): ProfileIntent()

    object SaveData: ProfileIntent()
    object Cancel: ProfileIntent()
}
