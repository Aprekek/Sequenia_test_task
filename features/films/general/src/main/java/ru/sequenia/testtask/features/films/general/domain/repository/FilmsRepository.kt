package ru.sequenia.testtask.features.films.general.domain.repository

import ru.sequenia.testtask.features.films.general.domain.entities.FilmAnnotation
import ru.sequenia.testtask.features.films.general.domain.entities.Genre

interface FilmsRepository {

	suspend fun fetchData()

	suspend fun getFilms(genreFilter: Long?): List<FilmAnnotation>

	suspend fun getGenres(): List<Genre>
}