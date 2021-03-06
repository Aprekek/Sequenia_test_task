package ru.sequenia.testtask.feature.films.details.data.datasource

import ru.sequenia.testtask.shared.films.data.dto.FilmWithGenresDto

interface FilmsDataSource {

	suspend fun getFilms(filmId: Long): FilmWithGenresDto
}