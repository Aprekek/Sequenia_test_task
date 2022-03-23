package ru.sequenia.testtask.shared.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.sequenia.testtask.shared.database.dto.FilmAnnotationDto
import ru.sequenia.testtask.shared.database.dto.FilmDto
import ru.sequenia.testtask.shared.database.dto.FilmWithGenresDto
import ru.sequenia.testtask.shared.database.dto.GenreWithFilmsDto

@Dao
interface FilmsDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(films: List<FilmDto>)

	@Query("SELECT * FROM films_table WHERE filmId = :filmId")
	suspend fun get(filmId: Long): FilmWithGenresDto

	@Query("SELECT filmId, localizedName, imageUrl FROM films_table ")
	suspend fun getAll(): List<FilmAnnotationDto>

	@Query("SELECT genreId FROM genre_table WHERE genreId = :genreId")
	suspend fun getAllWithFilter(genreId: Long): GenreWithFilmsDto
}