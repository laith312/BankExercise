package com.example.bankexercise.ui.playground

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bankexercise.repository.interfaces.AccountRepositoryInterface

class PlaygroundViewModel(
    private val repo: AccountRepositoryInterface
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text




}