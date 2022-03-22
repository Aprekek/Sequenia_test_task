package ru.sequenia.testtask.features.films.general.data.mappers

import ru.sequenia.testtask.features.films.general.data.models.FilmRemoteModel
import ru.sequenia.testtask.shared.database.dto.FilmDto

fun FilmRemoteModel.toDatabaseEntity() = FilmDto(id, localizedName, year, rating, imageUrl, description)