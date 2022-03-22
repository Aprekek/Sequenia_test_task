package ru.sequenia.testtask.features.films.general.presentation.ui.adapters.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.sequenia.testtask.features.films.general.databinding.HeaderItemLayoutBinding

class HeaderViewHolder private constructor(
	private val binding: HeaderItemLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

	companion object {

		fun from(parent: ViewGroup): HeaderViewHolder {
			val inflater = LayoutInflater.from(parent.context)
			val binding = HeaderItemLayoutBinding.inflate(inflater, parent, false)
			return HeaderViewHolder(binding)
		}
	}

	fun bind(title: String) {
		binding.title.text = title
	}
}