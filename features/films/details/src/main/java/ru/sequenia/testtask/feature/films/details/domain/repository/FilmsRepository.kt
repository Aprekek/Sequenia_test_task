package ru.sequenia.testtask.feature.films.details.domain.repository

import ru.sequenia.testtask.feature.films.details.domain.entities.Film

interface FilmsRepository {

	suspend fun getFilm(filmId: Long): Film
}