package com.example.moviecatalog.domain.model.profile

data class Profile(
    var id: String,
    var nickName: String?,
    var email: String,
    var avatarLink: String?,
    var name: String,
    var birthDate: String,
    var gender: Int
)
