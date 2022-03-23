package ru.sequenia.testtask.shared.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films_table")
data class FilmAnnotationDto(
	@PrimaryKey
	val filmId: Long,
	val localizedName: String,
	val imageUrl: String?
)