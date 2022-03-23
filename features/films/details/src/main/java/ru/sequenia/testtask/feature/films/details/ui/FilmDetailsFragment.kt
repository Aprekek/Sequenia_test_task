package ru.sequenia.testtask.feature.films.details.ui

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.sequenia.testtask.feature.films.details.R
import ru.sequenia.testtask.feature.films.details.databinding.FilmDetailsFragmentBinding
import ru.sequenia.testtask.feature.films.details.domain.entities.Film
import ru.sequenia.testtask.feature.films.details.presentation.contract.FilmDetailsContract
import ru.sequenia.testtask.feature.films.details.presentation.presenter.FilmDetailsPresenter
import ru.sequenia.testtask.shared.core.presentation.dispatchers.EventsDispatcher
import ru.sequenia.testtask.shared.core.presentation.fragments.BaseFragment

private const val FILM_ID = "FILM_ID"
private var Bundle.filmIdArg
	get() = getLong(FILM_ID)
	set(value) = putLong(FILM_ID, value)

class FilmDetailsFragment : BaseFragment<FilmDetailsFragmentBinding>(), FilmDetailsContract.View {

	companion object {

		fun getInstance(filmId: Long) = FilmDetailsFragment().apply {
			val bundle = Bundle().apply { filmIdArg = filmId }
			arguments = bundle
		}
	}

	private val presenter: FilmDetailsPresenter by viewModel()

	override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FilmDetailsFragmentBinding =
		FilmDetailsFragmentBinding.inflate(inflater, container, false)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		initListeners()

		presenter.onViewCreated(this)
		presenter.onInitialized(requireArguments().filmIdArg)
	}

	private fun initListeners() {
		binding.backButton.setOnClickListener {
			presenter.onExit()
		}
	}

	override fun showError() {
		binding.progressBar.visibility = View.GONE
		binding.generalFilmInfoLayout.visibility = View.GONE

		binding.notFoundErrorText.visibility = View.VISIBLE
	}

	override fun showLoading() {
		binding.generalFilmInfoLayout.visibility = View.GONE
		binding.notFoundErrorText.visibility = View.GONE

		binding.progressBar.visibility = View.VISIBLE
	}

	override fun showContent(film: Film) {
		setUpContent(film)

		binding.progressBar.visibility = View.GONE
		binding.notFoundErrorText.visibility = View.GONE

		binding.generalFilmInfoLayout.visibility = View.VISIBLE
	}

	private fun setUpContent(film: Film) {
		loadFilmImage(film.imageUrl)
		setUpFilmTextDescription(film)
	}

	private fun loadFilmImage(imageUrl: String?) {
		Glide.with(this)
			.load(imageUrl)
			.placeholder(ru.sequenia.testtask.shared.themes.R.drawable.ic_movie)
			.centerCrop()
			.into(binding.image)
	}

	private fun setUpFilmTextDescription(film: Film) {
		val spannableStringBuilder = SpannableStringBuilder()

		binding.toolbarTitle.text = film.localizedName

		binding.originalName.text = film.name

		binding.year.text = spannableStringBuilder.appendBold(
			getString(R.string.year_title) + " "
		).append(film.year.toString())
		spannableStringBuilder.clear()

		binding.rating.text = spannableStringBuilder.appendBold(
			getString(R.string.rating_title) + " "
		).append(film.rating?.toString() ?: getString(R.string.empty_data))
		spannableStringBuilder.clear()

		binding.genres.text = spannableStringBuilder.appendBold(
			getString(R.string.genre_title) + " "
		).append(
			film.genres
				.joinToString { it }
				.takeIf { it.isNotEmpty() }
				?: getString(R.string.empty_data)
		)
		spannableStringBuilder.clear()

		binding.description.text = film.description
	}

	override fun bind(dispatcher: EventsDispatcher<FilmDetailsContract.View>) {
		dispatcher.bind(viewLifecycleOwner, this)
	}
}

private fun SpannableStringBuilder.appendBold(string: String) = apply {
	val startIndex = length
	append(string)
	setSpan(StyleSpan(Typeface.BOLD), startIndex, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
}