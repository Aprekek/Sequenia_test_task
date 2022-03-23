package ru.sequenia.testtask.shared.films.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
	tableName = "genre_film_links_table",
	primaryKeys = ["genreId", "filmId"]
)
data class GenreFilmLinksDto(
	val genreId: Long,
	@ColumnInfo(index = true)
	val filmId: Long
)