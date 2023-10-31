package com.example.moviecatalog.presentation.screen.profilescreen

sealed class ProfileIntent{
    data class UpdateNickName(val nickName: String?): ProfileIntent()
    data class UpdateEmail(val email: String): ProfileIntent()
    data class UpdateAvatarLink(val link: String?): ProfileIntent()
    data class UpdateName(val name: String): ProfileIntent()
    object UpdateGender: ProfileIntent()
    data class UpdateDate(val date: String, val birthday: String): ProfileIntent()
    object UpdateDatePickerVisibility : ProfileIntent()

    data class UpdateEmailError(val error: Int?): ProfileIntent()

    object SaveData: ProfileIntent()
    object Cancel: ProfileIntent()
}
