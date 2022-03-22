package ru.sequenia.testtask.features.films.general.domain.usecases

import ru.sequenia.testtask.features.films.general.domain.entities.FilmAnnotation
import ru.sequenia.testtask.features.films.general.domain.repository.FilmsRepository

class GetFilmsUseCase(
	private val repository: FilmsRepository
) {

	suspend operator fun invoke(genreFilter: Long?): List<FilmAnnotation> =
		repository.getFilms(genreFilter)
}