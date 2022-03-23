package ru.sequenia.testtask.features.films.general.data.models

import ru.sequenia.testtask.shared.films.data.dto.FilmDto
import ru.sequenia.testtask.shared.films.data.dto.GenreDto
import ru.sequenia.testtask.shared.films.data.dto.GenreFilmLinksDto

data class FilmsInfoLocalModel(
	val films: List<FilmDto>,
	val genres: List<GenreDto>,
	val links: List<GenreFilmLinksDto>
)