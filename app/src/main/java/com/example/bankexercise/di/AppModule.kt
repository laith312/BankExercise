package com.example.bankexercise.di

import com.example.bankexercise.repository.DemoAccountRepository
import com.example.bankexercise.repository.interfaces.AccountRepositoryInterface
import com.example.bankexercise.service.AccountsApi
import com.example.bankexercise.ui.accounts.AccountsViewModel
import com.example.bankexercise.ui.transactions.TransactionViewModel
import com.example.bankexercise.ui.transfer.TransferViewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {

    single {
        Retrofit.Builder().baseUrl("https://myApiBase.com")
            .build()
            .create(AccountsApi::class.java)
    }

    /**
     * Demo Version of my repository - used to inject into viewModels
     */
    single<AccountRepositoryInterface> {
        DemoAccountRepository(get())
    }

    /**
     * Real repository
     */
//    single<AccountRepositoryInterface>(named("default")) {
//        AccountRepository(get())
//    }

    single {
        AccountsViewModel(get())
    }

    single {
        TransactionViewModel(get())
    }

    factory {
        TransferViewModel(get())
    }
}