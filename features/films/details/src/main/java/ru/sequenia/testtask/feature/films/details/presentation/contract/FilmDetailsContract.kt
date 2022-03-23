package ru.sequenia.testtask.feature.films.details.presentation.contract

import ru.sequenia.testtask.feature.films.details.domain.entities.Film
import ru.sequenia.testtask.shared.core.presentation.contracts.BaseContract

interface FilmDetailsContract : BaseContract {

	interface View : BaseContract.View<FilmDetailsContract.View> {

		fun showError()
		fun showLoading()
		fun showContent(film: Film)
	}

	interface Presenter : BaseContract.Presenter<FilmDetailsContract.View> {

		fun getFilmData(filmId: Long)
		fun onExit()
	}

	interface Model {

		var film: Film
	}
}