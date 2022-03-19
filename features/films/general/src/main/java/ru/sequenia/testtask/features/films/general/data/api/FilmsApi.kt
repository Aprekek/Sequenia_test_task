package ru.sequenia.testtask.features.films.general.data.api

import retrofit2.http.GET
import ru.sequenia.testtask.features.films.general.data.models.FilmsInfoRemoteModel

interface FilmsApi {

	@GET("films.json")
	suspend fun getFilms(): FilmsInfoRemoteModel
}