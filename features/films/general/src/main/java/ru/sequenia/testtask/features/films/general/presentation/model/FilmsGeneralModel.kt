package ru.sequenia.testtask.features.films.general.presentation.model

import ru.sequenia.testtask.features.films.general.domain.entities.FilmAnnotation
import ru.sequenia.testtask.features.films.general.presentation.contracts.FilmsGeneralContract

class FilmsGeneralModel : FilmsGeneralContract.Model {

	override var films: List<FilmAnnotation> = listOf()
	override var genres: List<GenreUiModel> = listOf()
	override var genreFilter: Long? = null
}