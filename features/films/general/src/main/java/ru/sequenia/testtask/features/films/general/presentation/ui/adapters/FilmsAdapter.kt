package ru.sequenia.testtask.features.films.general.presentation.ui.adapters

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.sequenia.testtask.features.films.general.domain.entities.FilmAnnotation
import ru.sequenia.testtask.features.films.general.presentation.ui.adapters.viewholders.FilmViewHolder

class FilmsAdapter(
	private val onClickAction: (film: FilmAnnotation) -> Unit,
	private val loadImageAction: (view: ImageView, url: String?) -> Unit
) : ListAdapter<FilmAnnotation, FilmViewHolder>(FilmsDiffUtil()) {

	companion object {

		const val VIEW_TYPE = 2
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder =
		FilmViewHolder.from(parent)

	override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
		holder.bind(getItem(position), onClickAction, loadImageAction)
	}

	override fun getItemViewType(position: Int): Int {
		return VIEW_TYPE
	}

	private class FilmsDiffUtil : DiffUtil.ItemCallback<FilmAnnotation>() {

		override fun areItemsTheSame(oldItem: FilmAnnotation, newItem: FilmAnnotation) =
			oldItem.id == newItem.id

		override fun areContentsTheSame(oldItem: FilmAnnotation, newItem: FilmAnnotation) =
			oldItem.id == newItem.id &&
				oldItem.imageUrl == oldItem.imageUrl &&
				oldItem.localizedName == oldItem.localizedName
	}
}