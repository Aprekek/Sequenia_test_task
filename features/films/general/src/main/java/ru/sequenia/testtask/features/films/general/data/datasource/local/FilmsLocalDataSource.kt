package ru.sequenia.testtask.features.films.general.data.datasource.local

import ru.sequenia.testtask.shared.database.dto.FilmAnnotationDto
import ru.sequenia.testtask.shared.database.dto.FilmDto

interface FilmsLocalDataSource {

	suspend fun insertFilms(films: List<FilmDto>)

	suspend fun getFilms(genreFilter: Long?): List<FilmAnnotationDto>
}