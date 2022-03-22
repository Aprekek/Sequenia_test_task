package ru.sequenia.testtask.shared.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import ru.sequenia.testtask.shared.database.dto.GenreDto

@Dao
interface GenreDao {

	@Insert(onConflict = REPLACE)
	suspend fun insert(genresList: List<GenreDto>)

	@Query("SELECT * FROM genre_table")
	suspend fun get(): List<GenreDto>
}