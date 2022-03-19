package ru.sequenia.testtask.features.films.general.domain.usecases

import ru.sequenia.testtask.features.films.general.domain.repository.FilmsRepository

class GetGenresUseCase(
	private val repository: FilmsRepository
) {

	suspend operator fun invoke() = repository.getGenres()
}