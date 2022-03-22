package ru.sequenia.testtask.navigation

import com.github.terrakok.cicerone.Cicerone

fun provideCicerone() = Cicerone.create().apply {
	router.newRootScreen(provideRootScreen())
}