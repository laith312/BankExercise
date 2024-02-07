package com.example.bankexercise.ui.accounts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankexercise.data.Account
import com.example.bankexercise.data.Card
import com.example.bankexercise.data.User
import com.example.bankexercise.repository.interfaces.AccountRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountsViewModel(
    private val repo: AccountRepositoryInterface
) : ViewModel() {

    private val _accounts = MutableLiveData<List<Account>>()
    val accounts: MutableLiveData<List<Account>> = _accounts

    private val _cards = MutableLiveData<List<Card>>()
    val cards: MutableLiveData<List<Card>> = _cards

    fun getAccounts() {
        // fetch logged in user from Cred. Holder
        val loggedInUser = User(0, "1", "Steve", emptyList())

        viewModelScope.launch(Dispatchers.IO) {
            val result = repo.fetchAccounts(loggedInUser)
            _accounts.postValue(result.data ?: emptyList())
        }
    }

    fun getCards() {
        // fetch logged in user from Cred. Holder
        val loggedInUser = User(0, "1", "Steve", emptyList())

        viewModelScope.launch(Dispatchers.IO) {
            val result = repo.fetchCards(loggedInUser)
            _cards.postValue(result.data ?: emptyList())
        }
    }
}