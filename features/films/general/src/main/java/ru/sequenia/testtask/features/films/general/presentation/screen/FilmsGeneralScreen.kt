package ru.sequenia.testtask.features.films.general.presentation.screen

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.sequenia.testtask.features.films.general.ui.FilmsGeneralFragment

fun getFilmsGeneralScreen() = FragmentScreen { FilmsGeneralFragment.getInstance() }