package com.example.bankexercise.repository

import com.example.bankexercise.data.Account
import com.example.bankexercise.data.AccountType
import com.example.bankexercise.data.Card
import com.example.bankexercise.data.Transaction
import com.example.bankexercise.data.TransactionType
import com.example.bankexercise.data.User
import com.example.bankexercise.repository.interfaces.AccountRepositoryInterface
import com.example.bankexercise.service.AccountsApi
import com.tps.challenge.other.Resource

/**
 * This is a Mock Repository, It will be used for Demo purposes. It can easily be swapped out
 * because it it injected using the KOIN DI framework.
 *
 * Data it build locally and store in lists and maps.
 */
class DemoAccountRepository(private val api: AccountsApi) : AccountRepositoryInterface {
    val userAccounts: MutableList<Account> = mutableListOf()
    val accountTransactions: MutableList<Transaction> = mutableListOf()
    val cardTransactions: HashMap<String, List<Transaction>> = hashMapOf()
    val userCreditCards: MutableList<Card> = mutableListOf()

    var forceNetworkError = false

    fun setForceHttpError(forceError: Boolean) {
        forceNetworkError = forceError
    }

    override suspend fun fetchAccounts(user: User): Resource<List<Account>> {
        return if (forceNetworkError) {
            Resource.error("Network ERROR", null)
        } else {
            Resource.success(userAccounts)
        }
    }

    override suspend fun fetchAccountTransactions(accountId: String): Resource<List<Transaction>> {
        return if (forceNetworkError) {
            Resource.error("Network ERROR", null)
        } else {
            Resource.success(accountTransactions)
        }
    }

    override suspend fun makeTransfer(
        fromAccount: Account,
        toAccount: Account,
        amount: Double
    ): Resource<Boolean> {
        return if (forceNetworkError) {
            Resource.error("Network ERROR", null)
        } else {
            accountTransactions.add(0,
                Transaction(
                    TransactionType.TRANSFER,
                    "Transfer from ${fromAccount.accountType} to ${toAccount.accountType} : $amount",
                    amount
                ))
            Resource.success(true)
        }
    }

    override suspend fun fetchCards(user: User): Resource<List<Card>> {
        return if (forceNetworkError) {
            Resource.error("Network ERROR", null)
        } else {
            Resource.success(userCreditCards)
        }
    }

    override suspend fun fetchCardTransactions(card: Card): Resource<List<Transaction>> {
        return if (forceNetworkError) {
            Resource.error("Network ERROR", null)
        } else {
            cardTransactions.get(card.cardId)?.let {
                Resource.success(it)
            } ?: Resource.error("Card Not Found ERROR", null)
        }
    }

    init {
        // populate data for demo
        userAccounts.addAll(
            listOf(
                Account(0, "000000301", 8475.35, AccountType.CHECKING),
                Account(1, "000123002", 600.28, AccountType.MY_FAMILY),
                Account(2, "022200123", 5721.36, AccountType.SAVINGS),
            )
        )

        accountTransactions.addAll(
            listOf(
                Transaction(
                    TransactionType.TRANSFER,
                    "Move to Savings",
                    75.00,
                    dateInMillis = 1703404800000
                ),
                Transaction(
                    TransactionType.PURCHASE,
                    "Mercado Gaudalajara, 255 Cabrillo Hwy S and some extra text as well",
                    23.99,
                    dateInMillis = 1703232000000
                ),
                Transaction(
                    TransactionType.PURCHASE,
                    "Move to Savings",
                    100.00,
                    dateInMillis = 1703232000000
                ),
                Transaction(
                    TransactionType.TRANSFER,
                    "Move to Family Account",
                    100.00,
                    dateInMillis = 1703318400000
                ),
                Transaction(
                    TransactionType.PURCHASE,
                    "Move to Savings",
                    100.00,
                    dateInMillis = 1702972800000
                ),
                Transaction(
                    TransactionType.DEPOSIT,
                    "Paycheck - Cabrillo Farms",
                    500.00,
                    dateInMillis = 1702800000000
                ),
                Transaction(
                    TransactionType.PURCHASE,
                    "Move to Savings",
                    100.00,
                    dateInMillis = 1702022400000
                ),
                Transaction(
                    TransactionType.TRANSFER,
                    "Move to Savings",
                    100.00,
                    dateInMillis = 1699948800000
                ),
                Transaction(
                    TransactionType.PURCHASE,
                    "Move to Savings",
                    100.00,
                    dateInMillis = 1698908400000
                ),
                Transaction(
                    TransactionType.DEPOSIT,
                    "Paycheck - Cabrillo Farms",
                    750.00,
                    dateInMillis = 1695452400000
                ),
            )
        )

        userCreditCards.addAll(
            listOf(
                Card(0, "11333100", "Salvador"),
                Card(1, "99432567", "Cynthia"),
            )
        )
        cardTransactions["11333100"] =
            accountTransactions.filter { it.transactionType != TransactionType.DEPOSIT }.map { it }
                .subList(0, accountTransactions.size / 2)
        cardTransactions["99432567"] =
            accountTransactions.filter { it.transactionType != TransactionType.DEPOSIT }
    }
}