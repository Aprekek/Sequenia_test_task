package ru.sequenia.testtask.features.films.general.data.mappers

import ru.sequenia.testtask.features.films.general.domain.entities.FilmAnnotation
import ru.sequenia.testtask.shared.database.dto.FilmAnnotationDto
import ru.sequenia.testtask.shared.database.dto.GenreWithFilmsDto

fun FilmAnnotationDto.toEntity() = FilmAnnotation(id = filmId, localizedName = localizedName, imageUrl = imageUrl)
fun List<FilmAnnotationDto>.toEntitiesList() = map(FilmAnnotationDto::toEntity)

fun GenreWithFilmsDto.toEntitiesList() = films.map(FilmAnnotationDto::toEntity)