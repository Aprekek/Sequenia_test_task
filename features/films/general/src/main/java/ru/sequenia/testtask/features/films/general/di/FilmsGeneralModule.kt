package ru.sequenia.testtask.features.films.general.di

import org.koin.dsl.module
import retrofit2.Retrofit
import ru.sequenia.testtask.features.films.general.data.api.FilmsApi
import ru.sequenia.testtask.features.films.general.data.datasource.local.FilmsLocalDataSource
import ru.sequenia.testtask.features.films.general.data.datasource.local.FilmsLocalDataSourceImpl
import ru.sequenia.testtask.features.films.general.data.datasource.local.GenreFilmLinksLocalDataSource
import ru.sequenia.testtask.features.films.general.data.datasource.local.GenreFilmLinksLocalDataSourceImpl
import ru.sequenia.testtask.features.films.general.data.datasource.local.GenreLocalDataSource
import ru.sequenia.testtask.features.films.general.data.datasource.local.GenreLocalDataSourceImpl
import ru.sequenia.testtask.features.films.general.data.datasource.remote.FilmsRemoteDataSource
import ru.sequenia.testtask.features.films.general.data.datasource.remote.FilmsRemoteDataSourceImpl
import ru.sequenia.testtask.features.films.general.data.mappers.FilmsInfoMapper
import ru.sequenia.testtask.features.films.general.data.repository.FilmsRepositoryImpl
import ru.sequenia.testtask.features.films.general.domain.repository.FilmsRepository
import ru.sequenia.testtask.features.films.general.domain.usecases.GetFilmsUseCase
import ru.sequenia.testtask.features.films.general.domain.usecases.GetGenresUseCase
import ru.sequenia.testtask.features.films.general.domain.usecases.UpdateFilmsDataUseCase

val filmsGeneralModule = module {

	factory<FilmsApi> { get<Retrofit>().create(FilmsApi::class.java) }

	factory<FilmsLocalDataSource> {
		FilmsLocalDataSourceImpl(dao = get())
	}
	factory<GenreLocalDataSource> {
		GenreLocalDataSourceImpl(dao = get())
	}
	factory<GenreFilmLinksLocalDataSource> {
		GenreFilmLinksLocalDataSourceImpl(dao = get())
	}
	factory<FilmsRemoteDataSource> {
		FilmsRemoteDataSourceImpl(api = get())
	}

	factory<FilmsRepository> { FilmsRepositoryImpl() }

	factory { FilmsInfoMapper(keyGenerator = get()) }

	factory { UpdateFilmsDataUseCase(repository = get()) }
	factory { GetFilmsUseCase(repository = get()) }
	factory { GetGenresUseCase(repository = get()) }
}