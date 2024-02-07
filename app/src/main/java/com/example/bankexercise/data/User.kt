package com.example.bankexercise.data

data class User(
    val id: Int,
    val userId: String,
    val name: String,
    val accounts: List<Account>
)
