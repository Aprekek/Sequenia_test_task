package ru.sequenia.testtask.feature.films.details.data.datasource

import ru.sequenia.testtask.shared.database.dao.FilmsDao
import ru.sequenia.testtask.shared.database.dto.FilmDto
import ru.sequenia.testtask.shared.database.dto.GenreDto

class FilmsDataSourceImpl(
	private val dao: FilmsDao
) : FilmsDataSource {

	override suspend fun getFilms(filmId: Long): Map<FilmDto, List<GenreDto>> = dao.get(filmId)
}