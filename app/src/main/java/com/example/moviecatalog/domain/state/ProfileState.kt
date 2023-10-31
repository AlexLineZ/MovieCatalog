package com.example.moviecatalog.domain.state

data class ProfileState (
    val nickName: String?,
    val email: String,
    val avatarLink: String?,
    val name: String,
    val gender: Int,
    val date: String,
    val birthday: String,

    val emailError: String?,

    val isDatePickerOpened: Boolean,
    val changesInProfile: Boolean
)