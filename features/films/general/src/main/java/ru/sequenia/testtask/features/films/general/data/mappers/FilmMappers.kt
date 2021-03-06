package ru.sequenia.testtask.features.films.general.data.mappers

import ru.sequenia.testtask.features.films.general.data.models.FilmRemoteModel
import ru.sequenia.testtask.shared.films.data.dto.FilmDto

fun FilmRemoteModel.toDatabaseEntity() = FilmDto(id, localizedName, name, year, rating, imageUrl, description)