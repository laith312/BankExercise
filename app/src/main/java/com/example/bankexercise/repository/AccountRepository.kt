package com.example.bankexercise.repository

import com.example.bankexercise.data.Account
import com.example.bankexercise.data.Card
import com.example.bankexercise.data.Transaction
import com.example.bankexercise.data.User
import com.example.bankexercise.repository.interfaces.AccountRepositoryInterface
import com.example.bankexercise.service.AccountsApi
import com.tps.challenge.other.Resource

/**
 * Handle Failed API Responses, Caching and business logic
 */
class AccountRepository(private val api: AccountsApi) : AccountRepositoryInterface {

    override suspend fun fetchAccounts(user: User): Resource<List<Account>> {
        return Resource.success(api.getUserAccounts(user.userId).body())
    }

    override suspend fun fetchAccountTransactions(accountId: String): Resource<List<Transaction>> {
        return Resource.success(api.getAccountTransactions(accountId).body())
    }

    override suspend fun makeTransfer(
        fromAccount: Account,
        toAccount: Account,
        amount: Double
    ): Resource<Boolean> {
        return Resource.success(
            api.transferFunds(
                fromAccount.accountNumber,
                toAccount.accountNumber,
                amount
            ).body()
        )
    }

    override suspend fun fetchCards(user: User): Resource<List<Card>> {
        return Resource.success(api.getAccountCards(user.userId).body())
    }

    override suspend fun fetchCardTransactions(card: Card): Resource<List<Transaction>> {
        return Resource.success(api.getCardTransactions(card.cardId).body())
    }
}