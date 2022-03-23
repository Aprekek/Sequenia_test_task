package ru.sequenia.testtask.features.films.general.data.mappers

import ru.sequenia.testtask.features.films.general.data.models.FilmRemoteModel
import ru.sequenia.testtask.features.films.general.data.models.FilmsInfoLocalModel
import ru.sequenia.testtask.features.films.general.data.models.FilmsInfoRemoteModel
import ru.sequenia.testtask.shared.database.KeyGenerator
import ru.sequenia.testtask.shared.database.dto.FilmDto
import ru.sequenia.testtask.shared.database.dto.GenreDto
import ru.sequenia.testtask.shared.database.dto.GenreFilmLinksDto

class FilmsInfoMapper(
	private val keyGenerator: KeyGenerator
) {

	fun map(response: FilmsInfoRemoteModel): FilmsInfoLocalModel {
		val links = mutableListOf<GenreFilmLinksDto>()
		val films = mutableListOf<FilmDto>()
		val genres = HashMap<String, Long>()

		response.films.forEach { filmRemoteModel ->
			mapFilm(film = filmRemoteModel, filmsContainer = films)

			filmRemoteModel.genres.forEach { genre ->
				var id = keyGenerator.getNewKey()

				id = mapGenre(genre = genre, id = id, genresContainer = genres)
				addLinkBetweenFilmAndGenre(filmId = filmRemoteModel.id, genreId = id, linksContainer = links)
			}
		}

		return FilmsInfoLocalModel(
			films = films,
			genres = genres.map { GenreDto(genreId = it.value, genreName = it.key) },
			links = links
		)
	}

	private fun mapFilm(
		film: FilmRemoteModel,
		filmsContainer: MutableList<FilmDto>
	) {
		filmsContainer.add(
			film.toDatabaseEntity()
		)
	}

	private fun mapGenre(
		genre: String,
		id: Long,
		genresContainer: HashMap<String, Long>
	) = genresContainer.getOrPut(genre) { id }

	private fun addLinkBetweenFilmAndGenre(
		filmId: Long,
		genreId: Long,
		linksContainer: MutableList<GenreFilmLinksDto>
	) {
		linksContainer.add(
			GenreFilmLinksDto(genreId, filmId)
		)
	}
}