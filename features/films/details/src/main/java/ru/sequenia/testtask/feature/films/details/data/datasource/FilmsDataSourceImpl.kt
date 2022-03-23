package ru.sequenia.testtask.feature.films.details.data.datasource

import ru.sequenia.testtask.shared.films.data.dao.FilmsDao
import ru.sequenia.testtask.shared.films.data.dto.FilmWithGenresDto

class FilmsDataSourceImpl(
	private val dao: FilmsDao
) : FilmsDataSource {

	override suspend fun getFilms(filmId: Long): FilmWithGenresDto = dao.get(filmId)
}