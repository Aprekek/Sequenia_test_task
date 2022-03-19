package ru.sequenia.testtask.network.di

import org.koin.dsl.module
import ru.sequenia.testtask.network.RetrofitProvider

val networkModule = module {

	single { RetrofitProvider.retrofit }
}