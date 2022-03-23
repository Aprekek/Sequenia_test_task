package ru.sequenia.testtask.features.films.general.presentation.ui.adapters.viewholders

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.AttrRes
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
		binding.genreName.setTextColor(
			getColorFromAttr(
				binding.root.context,
				if (genreUiModel.selected)
					com.google.android.material.R.attr.colorOnPrimary
				else
					com.google.android.material.R.attr.colorOnSurface
			)
		)

		binding.genreCard.isChecked = genreUiModel.selected

		binding.root.setOnClickListener {
			onClickAction(genreUiModel.genre.id)
		}
	}
}

private fun getColorFromAttr(context: Context, @AttrRes attr: Int): Int = TypedValue().run {
	context.theme.resolveAttribute(attr, this, true)
	data
}