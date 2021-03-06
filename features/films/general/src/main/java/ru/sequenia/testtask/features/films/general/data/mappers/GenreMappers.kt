package ru.sequenia.testtask.features.films.general.data.mappers

import ru.sequenia.testtask.features.films.general.domain.entities.Genre
import ru.sequenia.testtask.shared.films.data.dto.GenreDto

fun GenreDto.toEntity() = Genre(id = genreId, name = genreName)
fun List<GenreDto>.toEntitiesList() = map(GenreDto::toEntity)