package ru.sequenia.testtask.shared.films.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ru.sequenia.testtask.shared.films.data.dto.FilmAnnotationDto
import ru.sequenia.testtask.shared.films.data.dto.FilmDto
import ru.sequenia.testtask.shared.films.data.dto.FilmWithGenresDto
import ru.sequenia.testtask.shared.films.data.dto.GenreWithFilmsDto

@Dao
interface FilmsDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(films: List<FilmDto>)

	@Transaction
	@Query("SELECT * FROM films_table WHERE filmId = :filmId")
	suspend fun get(filmId: Long): FilmWithGenresDto

	@Query("SELECT filmId, localizedName, imageUrl FROM films_table ORDER BY localizedName")
	suspend fun getAll(): List<FilmAnnotationDto>

	@Transaction
	@Query("SELECT genreId FROM genre_table WHERE genreId = :genreId")
	suspend fun getAllWithFilter(genreId: Long): GenreWithFilmsDto
}