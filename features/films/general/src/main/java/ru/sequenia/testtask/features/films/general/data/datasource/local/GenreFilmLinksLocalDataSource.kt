package ru.sequenia.testtask.features.films.general.data.datasource.local

import ru.sequenia.testtask.shared.database.dto.GenreFilmLinksDto

interface GenreFilmLinksLocalDataSource {

	suspend fun insertLinks(links: List<GenreFilmLinksDto>)
}