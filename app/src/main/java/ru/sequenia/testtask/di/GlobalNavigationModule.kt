package ru.sequenia.testtask.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import org.koin.dsl.module
import ru.sequenia.testtask.navigation.provideCicerone

val globalNavigationModule = module {

	single { provideCicerone() }
	single { get<Cicerone<Router>>().router }
	single { get<Cicerone<Router>>().getNavigatorHolder() }
}