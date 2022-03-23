package ru.sequenia.testtask.feature.films.details.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sequenia.testtask.feature.films.details.data.datasource.FilmsDataSource
import ru.sequenia.testtask.feature.films.details.data.mappers.toEntity
import ru.sequenia.testtask.feature.films.details.domain.entities.Film
import ru.sequenia.testtask.feature.films.details.domain.repository.FilmsRepository

class FilmsRepositoryImpl(
	private val datasource: FilmsDataSource
) : FilmsRepository {

	override suspend fun getFilm(filmId: Long): Film =
		withContext(Dispatchers.IO) {
			datasource.getFilms(filmId).toEntity()
		}
}