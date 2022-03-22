package ru.sequenia.testtask.shared.database.dto

data class FilmAnnotationDto(
	val filmId: Long,
	val localizedName: String,
	val imageUrl: String?
)