package ru.sequenia.testtask.shared.database.dto

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class FilmWithGenresDto(
	@Embedded val filmDto: FilmDto,
	@Relation(
		parentColumn = "filmId",
		entityColumn = "genreId",
		associateBy = Junction(GenreFilmLinksDto::class)
	)
	val genres: List<GenreDto>
)