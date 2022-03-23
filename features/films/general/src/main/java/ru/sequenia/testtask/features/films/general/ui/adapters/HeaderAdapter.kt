package ru.sequenia.testtask.features.films.general.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.sequenia.testtask.features.films.general.ui.adapters.viewholders.HeaderViewHolder

class HeaderAdapter : ListAdapter<String, HeaderViewHolder>(HeaderDiffUtil()) {

	companion object {

		const val VIEW_TYPE = 0
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder =
		HeaderViewHolder.from(parent)

	override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
		holder.bind(getItem(position))
	}

	override fun getItemViewType(position: Int): Int {
		return VIEW_TYPE
	}

	private class HeaderDiffUtil : DiffUtil.ItemCallback<String>() {

		override fun areItemsTheSame(oldItem: String, newItem: String) =
			oldItem == newItem

		override fun areContentsTheSame(oldItem: String, newItem: String) =
			oldItem == newItem
	}
}