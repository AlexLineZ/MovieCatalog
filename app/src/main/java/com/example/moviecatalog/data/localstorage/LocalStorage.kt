package com.example.moviecatalog.data.localstorage

import android.content.Context
import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.data.model.TokenResponse

class LocalStorage(context: Context) {

    companion object {
        const val SHARED_PREF = "shared_pref"
        const val TOKEN_KEY = "token_key"
    }

    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)

    fun saveToken(token: TokenResponse) {
        sharedPreferences.edit().putString(TOKEN_KEY, token.token).apply()
    }

    fun getToken() : TokenResponse{
        return TokenResponse(
            sharedPreferences.getString(TOKEN_KEY, Constants.EMPTY_STRING)!!
        )
    }

    fun hasToken() : Boolean {
        return sharedPreferences.contains(TOKEN_KEY)
    }
}