package ru.sequenia.testtask.features.films.general.data.datasource.local

import ru.sequenia.testtask.shared.database.dto.GenreDto

interface GenreLocalDataSource {

	suspend fun insertGenres(genres: List<GenreDto>)

	suspend fun getGenres(): List<GenreDto>
}