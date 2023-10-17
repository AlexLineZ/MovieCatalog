package com.example.moviecatalog.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalog.presentation.ui.registrationscreen.RegistrationViewModel

class RegistrationViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegistrationViewModel() as T
    }
}