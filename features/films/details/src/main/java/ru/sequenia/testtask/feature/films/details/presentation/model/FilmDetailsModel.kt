package ru.sequenia.testtask.feature.films.details.presentation.model

import ru.sequenia.testtask.feature.films.details.domain.entities.Film
import ru.sequenia.testtask.feature.films.details.presentation.contract.FilmDetailsContract

data class FilmDetailsModel(override var film: Film) : FilmDetailsContract.Model