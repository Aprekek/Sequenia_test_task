package ru.sequenia.testtask.feature.films.details.data.mappers

import ru.sequenia.testtask.shared.films.data.dto.GenreDto

fun List<GenreDto>.toEntitiesList() = map { it.genreName }