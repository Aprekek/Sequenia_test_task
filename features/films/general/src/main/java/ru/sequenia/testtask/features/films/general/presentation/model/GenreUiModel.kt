package ru.sequenia.testtask.features.films.general.presentation.model

import ru.sequenia.testtask.features.films.general.domain.entities.Genre

data class GenreUiModel(
	val genre: Genre,
	val selected: Boolean
)