package ru.sequenia.testtask.feature.films.details.domain.usecases

import ru.sequenia.testtask.feature.films.details.domain.repository.FilmsRepository

class GetFilmUseCase(
	private val repository: FilmsRepository
) {

	suspend operator fun invoke(filmId: Long) = repository.getFilm(filmId)
}