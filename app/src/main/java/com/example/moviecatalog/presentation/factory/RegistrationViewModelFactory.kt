package com.example.moviecatalog.presentation.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalog.presentation.screen.registrationscreen.RegistrationViewModel

class RegistrationViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegistrationViewModel(application.applicationContext) as T
    }
}