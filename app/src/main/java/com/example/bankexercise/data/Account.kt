package com.example.bankexercise.data

import java.io.Serializable

data class Account(
    val id: Int,                                // DB primary Key
    val accountNumber: String,                 // server account number
    val accountBalance: Double = 0.00,
    val accountType: AccountType,
) : Serializable

enum class AccountType {
    CHECKING, MY_FAMILY, SAVINGS
}