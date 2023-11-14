package com.example.moviecatalog.data.localstorage

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.moviecatalog.common.Constants
import com.example.moviecatalog.data.model.TokenResponse

const val SHARED_PREF = "shared_pref"
const val TOKEN_KEY = "token_key"

class LocalStorage(context: Context) {
    //Ваня Гулевский сказал использовать Encripted

    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    private val sharedPreferences = EncryptedSharedPreferences.create(
        SHARED_PREF,
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

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

    fun removeToken() {
        sharedPreferences.edit().remove(TOKEN_KEY).apply()
    }
}