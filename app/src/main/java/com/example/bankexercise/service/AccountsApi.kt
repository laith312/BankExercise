package com.example.bankexercise.service

import com.example.bankexercise.data.Account
import com.example.bankexercise.data.Card
import com.example.bankexercise.data.Transaction
import retrofit2.Response

/**
 * This interface would use Retrofit2 to make network calls. see @see {@link UserApi}
 */
interface AccountsApi {

    suspend fun transferFunds(
        fromAccountId: String,
        toAccountId: String,
        amount: Double
    ): Response<Boolean>

    suspend fun getUserAccounts(accountId: String): Response<List<Account>>

    suspend fun getAccountTransactions(accountNumber: String): Response<List<Transaction>>

    suspend fun getAccountCards(accountId: String): Response<List<Card>>

    suspend fun getCardTransactions(cardId: String): Response<List<Transaction>>
}