package ru.sequenia.testtask.shared.database.dto

import androidx.room.Junction
import androidx.room.Relation

data class GenreWithFilmsDto(
	val genreId: Long,
	@Relation(
		parentColumn = "genreId",
		entityColumn = "filmId",
		associateBy = Junction(GenreFilmLinksDto::class)
	)
	val films: List<FilmAnnotationDto>
)