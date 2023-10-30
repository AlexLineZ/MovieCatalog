package com.example.moviecatalog.domain.state

data class ProfileState (
    val nickName: String,
    val email: String,
    val avatarLink: String?,
    val gender: Int,
    val date: String,
    val birthday: String,

    val isDatePickerOpened: Boolean,
    val changesInProfile: Boolean
)