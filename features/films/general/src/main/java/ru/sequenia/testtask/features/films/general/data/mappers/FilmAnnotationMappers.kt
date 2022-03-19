package ru.sequenia.testtask.features.films.general.data.mappers

import ru.sequenia.testtask.features.films.general.domain.entities.FilmAnnotation
import ru.sequenia.testtask.shared.database.dto.FilmAnnotationDto

fun FilmAnnotationDto.toEntity() = FilmAnnotation(id = filmId, localizedName = localizedName, imageUrl = imageUrl)
fun List<FilmAnnotationDto>.toEntitiesList() = map(FilmAnnotationDto::toEntity)