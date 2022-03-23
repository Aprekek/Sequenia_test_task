package ru.sequenia.testtask.di

import org.koin.dsl.module
import ru.sequenia.testtask.feature.films.details.presentation.router.FilmDetailsRouter
import ru.sequenia.testtask.features.films.general.presentation.router.FilmsGeneralRouter
import ru.sequenia.testtask.navigation.routers.FilmDetailsRouterImpl
import ru.sequenia.testtask.navigation.routers.FilmsGeneralRouterImpl

val routersModule = module {

	factory<FilmsGeneralRouter> { FilmsGeneralRouterImpl(router = get()) }
	factory<FilmDetailsRouter> { FilmDetailsRouterImpl(router = get()) }
}