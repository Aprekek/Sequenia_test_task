package ru.sequenia.testtask.feature.films.details.data.mappers

import ru.sequenia.testtask.shared.database.dto.GenreDto

fun List<GenreDto>.toEntitiesList() = map { it.genreName }