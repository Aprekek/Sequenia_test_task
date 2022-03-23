package ru.sequenia.testtask.features.films.general.data.datasource.local

import ru.sequenia.testtask.shared.films.data.dao.FilmsDao
import ru.sequenia.testtask.shared.films.data.dto.FilmAnnotationDto
import ru.sequenia.testtask.shared.films.data.dto.FilmDto
import ru.sequenia.testtask.shared.films.data.dto.GenreWithFilmsDto

class FilmsLocalDataSourceImpl(
	private val dao: FilmsDao
) : FilmsLocalDataSource {

	override suspend fun insertFilms(films: List<FilmDto>) {
		dao.insert(films)
	}

	override suspend fun getFilms(): List<FilmAnnotationDto> = dao.getAll()

	override suspend fun getFilms(genreFilter: Long): GenreWithFilmsDto = dao.getAllWithFilter(genreFilter)
}