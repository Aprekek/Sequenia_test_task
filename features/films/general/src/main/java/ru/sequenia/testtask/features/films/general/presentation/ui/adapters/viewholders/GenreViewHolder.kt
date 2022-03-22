package ru.sequenia.testtask.features.films.general.presentation.ui.adapters.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.sequenia.testtask.features.films.general.databinding.GenreItemLayoutBinding
import ru.sequenia.testtask.features.films.general.presentation.model.GenreUiModel

class GenreViewHolder private constructor(
	private val binding: GenreItemLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

	companion object {

		fun from(parent: ViewGroup): GenreViewHolder {
			val inflater = LayoutInflater.from(parent.context)
			val binding = GenreItemLayoutBinding.inflate(inflater, parent, false)
			return GenreViewHolder(binding)
		}
	}

	fun bind(genreUiModel: GenreUiModel, onClickAction: (Long) -> Unit) {
		binding.genreName.text = genreUiModel.genre.name
		binding.genreCard.isChecked = genreUiModel.selected

		binding.root.setOnClickListener {
			onClickAction(genreUiModel.genre.id)
		}
	}
}