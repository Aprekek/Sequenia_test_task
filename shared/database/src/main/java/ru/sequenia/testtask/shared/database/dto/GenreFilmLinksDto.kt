package ru.sequenia.testtask.shared.database.dto

import androidx.room.Entity

@Entity(
	tableName = "genre_film_links_table",
	primaryKeys = ["genreId", "filmId"]
)
data class GenreFilmLinksDto(
	val genreId: Long,
	val filmId: Long
)