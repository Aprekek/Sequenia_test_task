package ru.sequenia.testtask.feature.films.details.di

import org.koin.dsl.module
import ru.sequenia.testtask.feature.films.details.domain.usecases.GetFilmUseCase

val filmDetailsModule = module {

	factory { GetFilmUseCase(repository = get()) }
}