package ru.sequenia.testtask.feature.films.details.data.datasource

import ru.sequenia.testtask.shared.database.dto.FilmDto
import ru.sequenia.testtask.shared.database.dto.GenreDto

interface FilmsDataSource {

	suspend fun getFilms(filmId: Long): Map<FilmDto, List<GenreDto>>
}