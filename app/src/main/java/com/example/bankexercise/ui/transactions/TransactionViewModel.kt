package com.example.bankexercise.ui.transactions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankexercise.data.Card
import com.example.bankexercise.data.Transaction
import com.example.bankexercise.repository.interfaces.AccountRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransactionViewModel(private val repo: AccountRepositoryInterface) : ViewModel() {

    private val _transactions = MutableLiveData<List<Transaction>>()
    val transactions: MutableLiveData<List<Transaction>> = _transactions

    fun getAccountTransactions(accountId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repo.fetchAccountTransactions(accountId)
            _transactions.postValue(result.data ?: emptyList())
        }
    }

    fun getCardTransactions(card: Card) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repo.fetchCardTransactions(card)
            _transactions.postValue(result.data ?: emptyList())
        }
    }
}