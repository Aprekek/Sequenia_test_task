package ru.sequenia.testtask.features.films.general.data.datasource.local

import ru.sequenia.testtask.shared.database.dto.FilmAnnotationDto
import ru.sequenia.testtask.shared.database.dto.FilmDto
import ru.sequenia.testtask.shared.database.dto.GenreWithFilmsDto

interface FilmsLocalDataSource {

	suspend fun insertFilms(films: List<FilmDto>)

	suspend fun getFilms(): List<FilmAnnotationDto>

	suspend fun getFilms(genreFilter: Long): GenreWithFilmsDto
}