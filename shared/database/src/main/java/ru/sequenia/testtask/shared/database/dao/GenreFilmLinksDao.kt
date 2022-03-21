package ru.sequenia.testtask.shared.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Transaction
import ru.sequenia.testtask.shared.database.dto.FilmDto
import ru.sequenia.testtask.shared.database.dto.GenreDto
import ru.sequenia.testtask.shared.database.dto.GenreFilmLinksDto

@Dao
interface GenreFilmLinksDao {

	@Insert(onConflict = IGNORE)
	suspend fun insert(links: List<GenreFilmLinksDto>)

	@Transaction
	suspend fun insert(
		filmsDao: FilmsDao,
		filmsList: List<FilmDto>,
		genreDao: GenreDao,
		genresList: List<GenreDto>,
		links: List<GenreFilmLinksDto>
	) {
		filmsDao.insert(filmsList)
		genreDao.insert(genresList)
		insert(links)
	}
}