package ru.sequenia.testtask.features.films.general.presentation.ui.adapters.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import ru.sequenia.testtask.features.films.general.databinding.FilmItemLayoutBinding
import ru.sequenia.testtask.features.films.general.domain.entities.FilmAnnotation

class FilmViewHolder private constructor(
	private val binding: FilmItemLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

	companion object {

		fun from(parent: ViewGroup): FilmViewHolder {
			val inflater = LayoutInflater.from(parent.context)
			val binding = FilmItemLayoutBinding.inflate(inflater, parent, false)
			return FilmViewHolder(binding)
		}
	}

	fun bind(
		film: FilmAnnotation,
		onClickAction: (film: FilmAnnotation) -> Unit,
		loadImageAction: (view: ImageView, url: String?) -> Unit
	) {
		binding.title.text = film.localizedName
		loadImageAction(binding.image, film.imageUrl)

		binding.root.setOnClickListener {
			onClickAction(film)
		}
	}
}