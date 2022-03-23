package ru.sequenia.testtask.feature.films.details.data.mappers

import ru.sequenia.testtask.feature.films.details.domain.entities.Film
import ru.sequenia.testtask.shared.database.dto.FilmDto
import ru.sequenia.testtask.shared.database.dto.GenreDto

fun Map<FilmDto, List<GenreDto>>.toEntitiesList() = map {
	with(it.key) {
		Film(
			id = filmId,
			localizedName = localizedName,
			name = name,
			year = year,
			rating = rating,
			imageUrl = imageUrl,
			description = description,
			genres = it.value.toEntitiesList()
		)
	}
}