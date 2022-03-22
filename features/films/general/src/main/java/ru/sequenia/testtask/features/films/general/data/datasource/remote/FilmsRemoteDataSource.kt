package ru.sequenia.testtask.features.films.general.data.datasource.remote

import ru.sequenia.testtask.features.films.general.data.models.FilmsInfoRemoteModel

interface FilmsRemoteDataSource {

	suspend fun getFilms(): FilmsInfoRemoteModel
}