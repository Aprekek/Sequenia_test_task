package ru.sequenia.testtask.features.films.general.data.datasource.local

import ru.sequenia.testtask.shared.films.data.dto.GenreFilmLinksDto

interface GenreFilmLinksLocalDataSource {

	suspend fun insertLinks(links: List<GenreFilmLinksDto>)
}