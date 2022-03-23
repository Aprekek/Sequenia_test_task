package ru.sequenia.testtask.feature.films.details.presentation.presenter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.sequenia.testtask.feature.films.details.domain.usecases.GetFilmUseCase
import ru.sequenia.testtask.feature.films.details.presentation.contract.FilmDetailsContract
import ru.sequenia.testtask.feature.films.details.presentation.model.FilmDetailsModel
import ru.sequenia.testtask.feature.films.details.presentation.router.FilmDetailsRouter
import ru.sequenia.testtask.shared.core.presentation.dispatchers.EventsDispatcher

class FilmDetailsPresenter(
	private val router: FilmDetailsRouter,
	private val getFilmUseCase: GetFilmUseCase
) : ViewModel(), FilmDetailsContract.Presenter {

	private val eventsDispatcher = EventsDispatcher<FilmDetailsContract.View>()
	private lateinit var model: FilmDetailsContract.Model
	private var wasInitialized = false
	private var hasError = false

	private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
		Log.d(this::class.qualifiedName, throwable.message.toString())
		eventsDispatcher.dispatchEvent { showError() }
		hasError = true
	}

	override fun onViewCreated(view: FilmDetailsContract.View) {
		view.bind(eventsDispatcher)
	}

	override fun getFilmData(filmId: Long) {
		if (!wasInitialized && !hasError) {
			loadFilm(filmId)
		} else if (!hasError && wasInitialized) {
			getCachedFilmData()
		} else if (hasError) {
			eventsDispatcher.dispatchEvent { showError() }
		}
	}

	private fun loadFilm(filmId: Long) {
		eventsDispatcher.dispatchEvent { showLoading() }

		viewModelScope.launch(coroutineExceptionHandler) {
			model = FilmDetailsModel(getFilmUseCase(filmId))

			eventsDispatcher.dispatchEvent { showContent(model.film) }
			wasInitialized = true
		}
	}

	private fun getCachedFilmData() {
		eventsDispatcher.dispatchEvent { showContent(model.film) }
	}

	override fun onExit() {
		router.exit()
	}
}