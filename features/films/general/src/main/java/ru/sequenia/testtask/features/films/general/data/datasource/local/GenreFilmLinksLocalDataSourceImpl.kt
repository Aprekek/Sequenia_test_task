package ru.sequenia.testtask.features.films.general.data.datasource.local

import ru.sequenia.testtask.shared.database.dao.GenreFilmLinksDao
import ru.sequenia.testtask.shared.database.dto.GenreFilmLinksDto

class GenreFilmLinksLocalDataSourceImpl(
	private val dao: GenreFilmLinksDao
) : GenreFilmLinksLocalDataSource {

	override suspend fun insertLinks(links: List<GenreFilmLinksDto>) {
		dao.insert(links)
	}
}