package ru.sequenia.testtask.features.films.general.data.datasource.local

import ru.sequenia.testtask.shared.films.data.dao.GenreFilmLinksDao
import ru.sequenia.testtask.shared.films.data.dto.GenreFilmLinksDto

class GenreFilmLinksLocalDataSourceImpl(
	private val dao: GenreFilmLinksDao
) : GenreFilmLinksLocalDataSource {

	override suspend fun insertLinks(links: List<GenreFilmLinksDto>) {
		dao.insert(links)
	}
}