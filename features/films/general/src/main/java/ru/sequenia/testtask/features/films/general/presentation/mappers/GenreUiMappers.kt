package ru.sequenia.testtask.features.films.general.presentation.mappers

import ru.sequenia.testtask.features.films.general.domain.entities.Genre
import ru.sequenia.testtask.features.films.general.presentation.model.GenreUiModel

fun Genre.toUiModel() = GenreUiModel(this, false)
fun List<Genre>.toUiModelList() = map(Genre::toUiModel)