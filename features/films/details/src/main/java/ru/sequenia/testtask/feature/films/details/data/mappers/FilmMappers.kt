package ru.sequenia.testtask.feature.films.details.data.mappers

import ru.sequenia.testtask.feature.films.details.domain.entities.Film
import ru.sequenia.testtask.shared.database.dto.FilmWithGenresDto

fun FilmWithGenresDto.toEntity() = with(filmDto) {
	Film(
		id = filmId,
		localizedName = localizedName,
		name = name,
		year = year,
		rating = rating,
		imageUrl = imageUrl,
		description = description,
		genres = genres.toEntitiesList()
	)
}