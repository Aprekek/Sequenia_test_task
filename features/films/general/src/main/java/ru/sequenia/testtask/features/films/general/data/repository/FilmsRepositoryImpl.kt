package ru.sequenia.testtask.features.films.general.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject
import ru.sequenia.testtask.features.films.general.data.datasource.local.FilmsLocalDataSource
import ru.sequenia.testtask.features.films.general.data.datasource.local.GenreFilmLinksLocalDataSource
import ru.sequenia.testtask.features.films.general.data.datasource.local.GenreLocalDataSource
import ru.sequenia.testtask.features.films.general.data.datasource.remote.FilmsRemoteDataSource
import ru.sequenia.testtask.features.films.general.data.mappers.FilmsInfoMapper
import ru.sequenia.testtask.features.films.general.data.mappers.toEntitiesList
import ru.sequenia.testtask.features.films.general.domain.entities.FilmAnnotation
import ru.sequenia.testtask.features.films.general.domain.entities.Genre
import ru.sequenia.testtask.features.films.general.domain.repository.FilmsRepository

class FilmsRepositoryImpl : FilmsRepository {

	private val filmsRemoteDataSource: FilmsRemoteDataSource by inject(FilmsRemoteDataSource::class.java)
	private val filmsLocalDataSource: FilmsLocalDataSource by inject(FilmsLocalDataSource::class.java)
	private val genresLocalDataSource: GenreLocalDataSource by inject(GenreLocalDataSource::class.java)
	private val linksLocalDataSource: GenreFilmLinksLocalDataSource by inject(GenreFilmLinksLocalDataSource::class.java)
	private val filmsInfoMapper: FilmsInfoMapper by inject(FilmsInfoMapper::class.java)

	override suspend fun fetchData() {
		withContext(Dispatchers.IO) {
			val filmsInfoLocalModel = filmsInfoMapper.map(
				filmsRemoteDataSource.getFilms()
			)
			launch {
				filmsLocalDataSource.insertFilms(
					filmsInfoLocalModel.films
				)
			}
			launch {
				genresLocalDataSource.insertGenres(
					filmsInfoLocalModel.genres
				)
			}
			launch {
				linksLocalDataSource.insertLinks(
					filmsInfoLocalModel.links
				)
			}
		}
	}

	override suspend fun getFilms(genreFilter: Long?): List<FilmAnnotation> =
		withContext(Dispatchers.IO) {
			filmsLocalDataSource.getFilms(genreFilter).toEntitiesList()
		}

	override suspend fun getGenres(): List<Genre> =
		withContext(Dispatchers.IO) {
			genresLocalDataSource.getGenres().toEntitiesList()
		}
}