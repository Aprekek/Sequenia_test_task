package ru.sequenia.testtask.features.films.general.domain.entities

data class FilmAnnotation(
	val id: Long,
	val localizedName: String,
	val imageUrl: String?
)