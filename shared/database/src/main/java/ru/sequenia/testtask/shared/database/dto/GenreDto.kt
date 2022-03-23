package ru.sequenia.testtask.shared.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre_table")
data class GenreDto(
	@PrimaryKey
	val genreId: Long,
	val genreName: String
)