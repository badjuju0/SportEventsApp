package com.example.sporteventsapp.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sporteventsapp.api.Repository

class MainViewModelFactory(private val repository: Repository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}