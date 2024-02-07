package com.example.bankexercise.ui.transfer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankexercise.data.Account
import com.example.bankexercise.repository.interfaces.AccountRepositoryInterface
import com.tps.challenge.other.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TransferViewModel(val repo: AccountRepositoryInterface) : ViewModel() {

    private val _status = MutableLiveData<Resource<Boolean>>()
    val status: MutableLiveData<Resource<Boolean>> = _status

    private val _amount: MutableLiveData<Double> = MutableLiveData(0.00)
    val amount: MutableLiveData<Double> = _amount

    fun addAmount(newAmount: Double) {
        val current = _amount.value?.plus(newAmount)
        _amount.postValue(current!!)
    }

    fun sendTransfer(fromAccount: Account, toAccount: Account, amount: Double) {
        if (amount <= 0.00) {
            _status.postValue(Resource.error("Amount can not be $0.00", null))
            return
        }
        if (amount > fromAccount.accountBalance) {
            _status.postValue(Resource.error("Insufficient Funds in Checking Account", null))
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            _status.postValue(Resource.loading(null))
            delay(500)
            val result = repo.makeTransfer(fromAccount, toAccount, amount)
            _status.postValue(result)
        }
    }
}