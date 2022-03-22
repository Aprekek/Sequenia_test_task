package ru.sequenia.testtask.feature.films.details.di

import org.koin.dsl.module
import ru.sequenia.testtask.feature.films.details.data.datasource.FilmsDataSource
import ru.sequenia.testtask.feature.films.details.data.datasource.FilmsDataSourceImpl
import ru.sequenia.testtask.feature.films.details.data.repository.FilmsRepositoryImpl
import ru.sequenia.testtask.feature.films.details.domain.repository.FilmsRepository
import ru.sequenia.testtask.feature.films.details.domain.usecases.GetFilmUseCase

val filmDetailsModule = module {

	factory<FilmsDataSource> { FilmsDataSourceImpl(dao = get()) }

	factory<FilmsRepository> { FilmsRepositoryImpl(datasource = get()) }

	factory { GetFilmUseCase(repository = get()) }
}