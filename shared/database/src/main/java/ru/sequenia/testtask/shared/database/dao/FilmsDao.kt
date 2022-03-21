package ru.sequenia.testtask.shared.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.sequenia.testtask.shared.database.dto.FilmAnnotationDto
import ru.sequenia.testtask.shared.database.dto.FilmDto
import ru.sequenia.testtask.shared.database.dto.GenreDto

@Dao
interface FilmsDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(films: List<FilmDto>)

	@Query(
		"SELECT films_table.*, genre_table.* FROM films_table " +
			"JOIN (SELECT genreId AS genre_id, filmId AS film_id" +
			"		FROM genre_film_links_table " +
			"		WHERE filmId = :filmId) " +
			"ON filmId = film_id " +
			"JOIN genre_table ON genreId = genre_id "
	)
	suspend fun get(filmId: Long): Map<FilmDto, List<GenreDto>>

	@Query("SELECT filmId, localizedName, imageUrl FROM films_table ")
	suspend fun getAll(): List<FilmAnnotationDto>

	@Query(
		"SELECT filmId, localizedName, imageUrl FROM films_table " +
			"JOIN (SELECT genreId AS genre_id, filmId AS film_id" +
			"		FROM genre_film_links_table " +
			"		WHERE genre_Id = :genreId " +
			") ON filmId = film_id "
	)
	suspend fun getAllWithFilter(genreId: Long): List<FilmAnnotationDto>
}