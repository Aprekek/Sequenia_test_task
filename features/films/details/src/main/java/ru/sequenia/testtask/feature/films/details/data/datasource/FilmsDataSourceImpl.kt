package ru.sequenia.testtask.feature.films.details.data.datasource

import ru.sequenia.testtask.shared.database.dao.FilmsDao
import ru.sequenia.testtask.shared.database.dto.FilmWithGenresDto

class FilmsDataSourceImpl(
	private val dao: FilmsDao
) : FilmsDataSource {

	override suspend fun getFilms(filmId: Long): FilmWithGenresDto = dao.get(filmId)
}