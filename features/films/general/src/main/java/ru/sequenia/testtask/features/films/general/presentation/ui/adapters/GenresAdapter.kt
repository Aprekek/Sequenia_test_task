package ru.sequenia.testtask.features.films.general.presentation.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.sequenia.testtask.features.films.general.presentation.model.GenreUiModel
import ru.sequenia.testtask.features.films.general.presentation.ui.adapters.viewholders.GenreViewHolder

class GenresAdapter(
	private val onClickAction: (Long) -> Unit
) : ListAdapter<GenreUiModel, GenreViewHolder>(GenreDiffUtil()) {

	companion object {

		const val VIEW_TYPE = 1
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder =
		GenreViewHolder.from(parent)

	override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
		holder.bind(getItem(position), onClickAction)
	}

	override fun getItemViewType(position: Int): Int {
		return VIEW_TYPE
	}

	private class GenreDiffUtil : DiffUtil.ItemCallback<GenreUiModel>() {

		override fun areItemsTheSame(oldItem: GenreUiModel, newItem: GenreUiModel) =
			oldItem.genre.name == newItem.genre.name

		override fun areContentsTheSame(oldItem: GenreUiModel, newItem: GenreUiModel) =
			oldItem == newItem
	}
}