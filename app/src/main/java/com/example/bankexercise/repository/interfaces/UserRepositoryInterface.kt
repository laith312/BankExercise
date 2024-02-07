package com.example.bankexercise.repository.interfaces

import com.example.bankexercise.data.User

interface UserRepositoryInterface {
    suspend fun userLogin(userName:String, password: String)

    suspend fun userLogout(userId: String)

    suspend fun updateUser(user: User)
}