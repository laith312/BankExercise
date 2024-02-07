package com.example.bankexercise.data

class Transaction(
    val transactionType: TransactionType,
    val message: String,
    val amount: Double = 0.00,
    val transactionId: String = getRandomString(10),
    val dateInMillis: Long = System.currentTimeMillis(),
)


fun getRandomString(length: Int): String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}

enum class TransactionType {
    DEPOSIT, WITHDRAWAL, TRANSFER, PURCHASE
}

