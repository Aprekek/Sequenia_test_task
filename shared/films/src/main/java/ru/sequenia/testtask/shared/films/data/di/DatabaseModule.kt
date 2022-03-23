package ru.sequenia.testtask.shared.films.data.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.sequenia.testtask.shared.films.data.KeyGenerator
import ru.sequenia.testtask.shared.films.data.database.FilmsDataBase

val sharedFilmsModule = module {

	single {
		Room.databaseBuilder(
			androidContext(),
			FilmsDataBase::class.java,
			FilmsDataBase.NAME
		).build()
	}

	factory { get<FilmsDataBase>().filmsDao() }
	factory { get<FilmsDataBase>().genreDao() }
	factory { get<FilmsDataBase>().genreFilmLinksDao() }

	factory { KeyGenerator() }
}