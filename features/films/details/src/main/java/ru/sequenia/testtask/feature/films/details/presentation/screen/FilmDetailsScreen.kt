package ru.sequenia.testtask.feature.films.details.presentation.screen

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.sequenia.testtask.feature.films.details.presentation.ui.FilmDetailsFragment

fun getFilmDetailsScreen(filmId: Long) = FragmentScreen { FilmDetailsFragment.getInstance(filmId) }