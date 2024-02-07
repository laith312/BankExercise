package com.example.bankexercise.repository

import com.example.bankexercise.data.User
import com.example.bankexercise.repository.interfaces.UserRepositoryInterface
import com.example.bankexercise.service.UserApi

class UserRepository(private val api: UserApi): UserRepositoryInterface {

    override suspend fun userLogin(userName: String, password: String) {
        api.login(userName, password)
    }

    override suspend fun userLogout(userId: String) {
        api.logout(userId)
    }

    override suspend fun updateUser(user: User) {
        api.updateUser(user.userId, user.name)
    }
}