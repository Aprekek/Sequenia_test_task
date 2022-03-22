package ru.sequenia.testtask.features.films.general.presentation.presenter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.sequenia.testtask.features.films.general.domain.entities.FilmAnnotation
import ru.sequenia.testtask.features.films.general.domain.usecases.GetFilmsUseCase
import ru.sequenia.testtask.features.films.general.domain.usecases.GetGenresUseCase
import ru.sequenia.testtask.features.films.general.domain.usecases.UpdateFilmsDataUseCase
import ru.sequenia.testtask.features.films.general.presentation.contracts.FilmsGeneralContract
import ru.sequenia.testtask.features.films.general.presentation.mappers.toUiModelList
import ru.sequenia.testtask.features.films.general.presentation.model.FilmsGeneralModel
import ru.sequenia.testtask.features.films.general.presentation.router.FilmsGeneralRouter
import ru.sequenia.testtask.shared.core.presentation.dispatchers.EventsDispatcher

class FilmsGeneralPresenter(
	private val router: FilmsGeneralRouter,
	private val updateFilmsDataUseCase: UpdateFilmsDataUseCase,
	private val getFilmsUseCase: GetFilmsUseCase,
	private val getGenresUseCase: GetGenresUseCase
) : ViewModel(), FilmsGeneralContract.Presenter {

	private val eventsDispatcher = EventsDispatcher<FilmsGeneralContract.View>()
	private val model: FilmsGeneralContract.Model = FilmsGeneralModel()
	private var wasInitialized = false

	private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
		Log.d(this::class.qualifiedName, throwable.message.toString())
		eventsDispatcher.dispatchEvent { showError() }
	}

	override fun onViewCreated(view: FilmsGeneralContract.View) {
		view.bind(eventsDispatcher)

		if (!wasInitialized) {
			loadFilmsFirstTime()
			wasInitialized = true
		} else {
			loadCachedFilms()
		}
	}

	private fun loadFilmsFirstTime() {
		eventsDispatcher.dispatchEvent { showLoading() }

		viewModelScope.launch(coroutineExceptionHandler) {
			updateFilmsDataUseCase()
			model.films = getFilmsUseCase(model.genreFilter)
			model.genres = getGenresUseCase().toUiModelList()

			eventsDispatcher.dispatchEvent { showContent(model.genres, model.films) }
		}
	}

	private fun loadCachedFilms() {
		eventsDispatcher.dispatchEvent { showContent(model.genres, model.films) }
	}

	override fun onReload() {
		loadFilmsFirstTime()
	}

	override fun onFilmSelect(film: FilmAnnotation) {
		router.navigateToFilmDetailsScree()
	}

	override fun onGenreFilterSelect(genreFilter: Long) {
		viewModelScope.launch {
			model.genreFilter = if (genreFilter == model.genreFilter) null else genreFilter
			model.genres = model.genres.map {
				it.copy(selected = it.genre.id == genreFilter && model.genreFilter != null)
			}
			model.films = getFilmsUseCase(model.genreFilter)

			eventsDispatcher.dispatchEvent { showContent(model.genres, model.films) }
		}
	}
}