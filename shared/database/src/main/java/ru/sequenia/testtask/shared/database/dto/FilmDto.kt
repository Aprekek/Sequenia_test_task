package ru.sequenia.testtask.shared.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films_table")
data class FilmDto(
	@PrimaryKey
	val filmId: Long,
	val localizedName: String,
	val year: Int,
	val rating: Float?,
	val imageUrl: String?,
	val description: String?
)