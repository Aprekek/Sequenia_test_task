package ru.sequenia.testtask.navigation.routers

import com.github.terrakok.cicerone.Router
import ru.sequenia.testtask.feature.films.details.presentation.router.FilmDetailsRouter

class FilmDetailsRouterImpl(
	private val router: Router
) : FilmDetailsRouter {

	override fun exit() {
		router.exit()
	}
}