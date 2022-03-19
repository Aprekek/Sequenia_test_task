package ru.sequenia.testtask.features.films.general.di

import org.koin.dsl.module
import ru.sequenia.testtask.features.films.general.domain.usecases.GetFilmsUseCase
import ru.sequenia.testtask.features.films.general.domain.usecases.GetGenresUseCase
import ru.sequenia.testtask.features.films.general.domain.usecases.UpdateFilmsDataUseCase

val filmsGeneralModule = module {

	factory { UpdateFilmsDataUseCase(repository = get()) }
	factory { GetFilmsUseCase(repository = get()) }
	factory { GetGenresUseCase(repository = get()) }
}