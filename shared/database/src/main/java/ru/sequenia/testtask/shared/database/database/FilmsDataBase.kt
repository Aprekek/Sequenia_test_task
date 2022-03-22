package ru.sequenia.testtask.shared.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.sequenia.testtask.shared.database.dao.FilmsDao
import ru.sequenia.testtask.shared.database.dao.GenreDao
import ru.sequenia.testtask.shared.database.dao.GenreFilmLinksDao
import ru.sequenia.testtask.shared.database.dto.FilmDto
import ru.sequenia.testtask.shared.database.dto.GenreDto
import ru.sequenia.testtask.shared.database.dto.GenreFilmLinksDto

@Database(
	entities = [
		FilmDto::class,
		GenreDto::class,
		GenreFilmLinksDto::class
	],
	version = 1,
	exportSchema = false
)
abstract class FilmsDataBase : RoomDatabase() {

	companion object {

		const val NAME = "films_database"
	}

	abstract fun filmsDao(): FilmsDao
	abstract fun genreDao(): GenreDao
	abstract fun genreFilmLinksDao(): GenreFilmLinksDao
}