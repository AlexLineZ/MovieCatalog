package com.example.moviecatalog.data.network.interceptor

import com.example.moviecatalog.domain.usecase.GetTokenFromLocalStorageUseCase
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    private var authToken: String? = null

    fun setAuthToken(token: String) {
        authToken = token
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        authToken?.let {
            val authHeader = "Bearer $it"
            request = request.newBuilder()
                .addHeader("Authorization", authHeader)
                .build()
        }

        return chain.proceed(request)
    }
}