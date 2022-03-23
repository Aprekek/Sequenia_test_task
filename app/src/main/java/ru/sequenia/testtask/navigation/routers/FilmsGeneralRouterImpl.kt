package ru.sequenia.testtask.navigation.routers

import com.github.terrakok.cicerone.Router
import ru.sequenia.testtask.feature.films.details.presentation.screen.getFilmDetailsScreen
import ru.sequenia.testtask.features.films.general.presentation.router.FilmsGeneralRouter

class FilmsGeneralRouterImpl(
	private val router: Router
) : FilmsGeneralRouter {

	override fun navigateToFilmDetailsScreen(filmId: Long) {
		router.navigateTo(getFilmDetailsScreen(filmId))
	}
}