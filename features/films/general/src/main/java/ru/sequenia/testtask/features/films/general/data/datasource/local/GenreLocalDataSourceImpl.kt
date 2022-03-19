package ru.sequenia.testtask.features.films.general.data.datasource.local

import ru.sequenia.testtask.shared.database.dao.GenreDao
import ru.sequenia.testtask.shared.database.dto.GenreDto

class GenreLocalDataSourceImpl(
	private val dao: GenreDao
) : GenreLocalDataSource {

	override suspend fun insertGenres(genres: List<GenreDto>) {
		dao.insert(genres)
	}

	override suspend fun getGenres(): List<GenreDto> = dao.get()
}