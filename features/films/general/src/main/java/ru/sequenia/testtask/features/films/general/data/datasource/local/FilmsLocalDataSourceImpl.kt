package ru.sequenia.testtask.features.films.general.data.datasource.local

import ru.sequenia.testtask.shared.database.dao.FilmsDao
import ru.sequenia.testtask.shared.database.dto.FilmAnnotationDto
import ru.sequenia.testtask.shared.database.dto.FilmDto

class FilmsLocalDataSourceImpl(
	private val dao: FilmsDao
) : FilmsLocalDataSource {

	override suspend fun insertFilms(films: List<FilmDto>) {
		dao.insert(films)
	}

	override suspend fun getFilms(genreFilter: Long?): List<FilmAnnotationDto> =
		if (genreFilter != null)
			dao.getAllWithFilter(genreFilter)
		else
			dao.getAll()
}