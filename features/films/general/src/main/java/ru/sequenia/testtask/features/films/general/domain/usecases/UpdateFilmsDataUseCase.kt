package ru.sequenia.testtask.features.films.general.domain.usecases

import ru.sequenia.testtask.features.films.general.domain.repository.FilmsRepository

data class UpdateFilmsDataUseCase(
	private val repository: FilmsRepository
) {

	suspend operator fun invoke() {
		repository.fetchData()
	}
}