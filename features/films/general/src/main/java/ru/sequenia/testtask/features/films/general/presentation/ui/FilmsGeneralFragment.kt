package ru.sequenia.testtask.features.films.general.presentation.ui

import android.content.res.Configuration
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.sequenia.testtask.features.films.general.R
import ru.sequenia.testtask.features.films.general.databinding.FilmsGeneralFragmentBinding
import ru.sequenia.testtask.features.films.general.domain.entities.FilmAnnotation
import ru.sequenia.testtask.features.films.general.presentation.contracts.FilmsGeneralContract
import ru.sequenia.testtask.features.films.general.presentation.model.GenreUiModel
import ru.sequenia.testtask.features.films.general.presentation.presenter.FilmsGeneralPresenter
import ru.sequenia.testtask.features.films.general.presentation.ui.adapters.FilmsAdapter
import ru.sequenia.testtask.features.films.general.presentation.ui.adapters.GenresAdapter
import ru.sequenia.testtask.features.films.general.presentation.ui.adapters.HeaderAdapter
import ru.sequenia.testtask.features.films.general.presentation.ui.adapters.decorators.FilmItemDecorator
import ru.sequenia.testtask.shared.core.presentation.dispatchers.EventsDispatcher
import ru.sequenia.testtask.shared.core.presentation.fragments.BaseFragment

class FilmsGeneralFragment : BaseFragment<FilmsGeneralFragmentBinding>(), FilmsGeneralContract.View {

	companion object {

		fun getInstance() = FilmsGeneralFragment()

		private const val SPAN_COUNT_PORTRAIT = 2
		private const val SPAN_COUNT_LANDSCAPE = 4

		private const val DELAY_TO_START_SCROLLING = 300L
		private const val MILLISECONDS_PER_INCH = 40f
	}

	private val presenter: FilmsGeneralPresenter by viewModel()

	private lateinit var concatAdapter: ConcatAdapter
	private lateinit var genresHeaderAdapter: HeaderAdapter
	private lateinit var filmsHeaderAdapter: HeaderAdapter
	private lateinit var genresAdapter: GenresAdapter
	private lateinit var filmsAdapter: FilmsAdapter

	private val scroller by lazy {
		object : LinearSmoothScroller(requireContext()) {
			override fun getVerticalSnapPreference(): Int {
				return SNAP_TO_START
			}

			override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
				return MILLISECONDS_PER_INCH / displayMetrics.densityDpi
			}
		}
	}

	override fun getBinding(inflater: LayoutInflater, container: ViewGroup?) =
		FilmsGeneralFragmentBinding.inflate(inflater, container, false)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		initAdapters()
		setupHeaders()
		initListeners()

		presenter.onViewCreated(this)
		presenter.loadFilmsData()
	}

	private fun initAdapters() {
		val spanCount = if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
			SPAN_COUNT_PORTRAIT
		} else {
			SPAN_COUNT_LANDSCAPE
		}

		genresHeaderAdapter = HeaderAdapter()
		filmsHeaderAdapter = HeaderAdapter()
		genresAdapter = GenresAdapter(onClickAction = ::onGenreClickAction)
		filmsAdapter = FilmsAdapter(
			onClickAction = ::onFilmClickAction,
			loadImageAction = ::loadImageForItem
		)

		val configuration = ConcatAdapter.Config.Builder().setIsolateViewTypes(false).build()
		concatAdapter = ConcatAdapter(
			configuration,
			genresHeaderAdapter, genresAdapter, filmsHeaderAdapter, filmsAdapter
		)

		val layoutManager = GridLayoutManager(
			requireContext(),
			spanCount,
			GridLayoutManager.VERTICAL,
			false
		).apply {
			spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
				override fun getSpanSize(position: Int): Int {
					return when (val viewType = concatAdapter.getItemViewType(position)) {
						HeaderAdapter.VIEW_TYPE -> spanCount
						GenresAdapter.VIEW_TYPE -> spanCount
						FilmsAdapter.VIEW_TYPE  -> 1
						else                    -> throw IllegalStateException("Unknown view type $viewType")
					}
				}
			}
		}

		binding.recyclerView.layoutManager = layoutManager
		binding.recyclerView.adapter = concatAdapter
		binding.recyclerView.addItemDecoration(
			FilmItemDecorator(
				spanCount = spanCount,
				startOffset = resources.getDimensionPixelOffset(ru.sequenia.testtask.shared.themes.R.dimen.small_margin_padding)
			)
		)
	}

	private fun setupHeaders() {
		genresHeaderAdapter.submitList(
			listOf(
				resources.getString(R.string.genres_title)
			)
		)
		filmsHeaderAdapter.submitList(
			listOf(
				resources.getString(R.string.films_title)
			)
		)
	}

	private fun initListeners() {
		binding.repeatText.setOnClickListener {
			presenter.onReload()
		}
	}

	private fun onGenreClickAction(genreId: Long) {
		presenter.onGenreFilterSelect(genreId)
		lifecycleScope.launch {
			delay(DELAY_TO_START_SCROLLING)

			scroller.targetPosition = genresAdapter.itemCount + genresHeaderAdapter.itemCount
			binding.recyclerView.layoutManager?.startSmoothScroll(scroller)
		}
	}

	private fun loadImageForItem(view: ImageView, url: String?) {
		if (url != null) {
			Glide.with(this)
				.load(url)
				.centerCrop()
				.diskCacheStrategy(DiskCacheStrategy.ALL)
				.placeholder(ru.sequenia.testtask.shared.themes.R.drawable.ic_movie)
				.into(view)
		} else {
			view.setImageDrawable(
				ContextCompat.getDrawable(
					requireContext(),
					ru.sequenia.testtask.shared.themes.R.drawable.ic_movie
				)
			)
		}
	}

	private fun onFilmClickAction(film: FilmAnnotation) {
		presenter.onFilmSelect(film)
	}

	override fun showLoading() {
		binding.recyclerView.visibility = View.GONE
		binding.loadingErrorContent.visibility = View.GONE

		binding.progressBar.visibility = View.VISIBLE
	}

	override fun showError() {
		binding.recyclerView.visibility = View.GONE
		binding.progressBar.visibility = View.GONE

		binding.loadingErrorContent.visibility = View.VISIBLE
	}

	override fun showContent(genres: List<GenreUiModel>, films: List<FilmAnnotation>) {
		binding.progressBar.visibility = View.GONE
		binding.loadingErrorContent.visibility = View.GONE

		binding.recyclerView.visibility = View.VISIBLE

		genresAdapter.submitList(genres)
		filmsAdapter.submitList(films)
	}

	override fun bind(dispatcher: EventsDispatcher<FilmsGeneralContract.View>) {
		dispatcher.bind(viewLifecycleOwner, this)
	}
}