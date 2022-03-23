package ru.sequenia.testtask.features.films.general.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.sequenia.testtask.features.films.general.ui.adapters.viewholders.HeaderViewHolder

class HeaderAdapter(
	private var header: String
) : RecyclerView.Adapter<HeaderViewHolder>() {

	companion object {

		const val VIEW_TYPE = 0
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder =
		HeaderViewHolder.from(parent)

	override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
		holder.bind(header)
	}

	override fun getItemViewType(position: Int): Int = VIEW_TYPE

	override fun getItemCount(): Int = 1
}