package ru.sequenia.testtask.features.films.general.presentation.contracts

import ru.sequenia.testtask.features.films.general.domain.entities.FilmAnnotation
import ru.sequenia.testtask.features.films.general.presentation.model.GenreUiModel
import ru.sequenia.testtask.shared.core.presentation.contracts.BaseContract

interface FilmsGeneralContract : BaseContract {

	interface View : BaseContract.View<FilmsGeneralContract.View> {

		fun showLoading()
		fun showError()
		fun showContent(genres: List<GenreUiModel>, films: List<FilmAnnotation>)
	}

	interface Presenter : BaseContract.Presenter<FilmsGeneralContract.View> {

		fun loadFilmsData()
		fun onReload()
		fun onFilmSelect(film: FilmAnnotation)
		fun onGenreFilterSelect(genreFilter: Long)
	}

	interface Model {

		var films: List<FilmAnnotation>
		var genres: List<GenreUiModel>
		var genreFilter: Long?
	}
}