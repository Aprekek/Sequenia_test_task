package ru.sequenia.testtask.features.films.general.data.models

import com.squareup.moshi.Json

data class FilmRemoteModel(
	val id: Long,
	@Json(name = "localized_name")
	val localizedName: String,
	val year: Int,
	val rating: Float?,
	@Json(name = "image_url")
	val imageUrl: String?,
	val description: String?,
	val genres: List<String>
)