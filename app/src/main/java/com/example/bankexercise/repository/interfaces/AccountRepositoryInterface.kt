package com.example.bankexercise.repository.interfaces

import com.example.bankexercise.data.Account
import com.example.bankexercise.data.Card
import com.example.bankexercise.data.Transaction
import com.example.bankexercise.data.User
import com.tps.challenge.other.Resource

interface AccountRepositoryInterface {

    suspend fun fetchAccounts(user: User): Resource<List<Account>>

    suspend fun fetchAccountTransactions(accountId: String): Resource<List<Transaction>>

    suspend fun makeTransfer(fromAccount: Account, toAccount:Account, amount: Double): Resource<Boolean>

    suspend fun fetchCards(user:User): Resource<List<Card>>

    suspend fun fetchCardTransactions(card: Card): Resource<List<Transaction>>
}