package ru.sequenia.testtask.features.films.general.data.datasource.remote

import ru.sequenia.testtask.features.films.general.data.api.FilmsApi
import ru.sequenia.testtask.features.films.general.data.models.FilmsInfoRemoteModel

class FilmsRemoteDataSourceImpl(
	private val api: FilmsApi
) : FilmsRemoteDataSource {

	override suspend fun getFilms(): FilmsInfoRemoteModel = api.getFilms()
}