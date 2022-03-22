package ru.sequenia.testtask.features.films.general.presentation.ui.adapters.decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.sequenia.testtask.features.films.general.presentation.ui.adapters.FilmsAdapter

class FilmItemDecorator(
	private val spanCount: Int,
	private val startOffset: Int
) : RecyclerView.ItemDecoration() {

	override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
		super.getItemOffsets(outRect, view, parent, state)

		var otherItemsCount = 0
		val adapters = (parent.adapter as ConcatAdapter).adapters
		for (i in 0 until adapters.size) {
			if (adapters[i] is FilmsAdapter)
				break
			else {
				otherItemsCount += adapters[i].itemCount
			}
		}

		val position = parent.getChildAdapterPosition(view)
		// If not a film item
		if (position < otherItemsCount)
			return

		if ((position - otherItemsCount) % spanCount == 0)
			outRect.set(startOffset, 0, 0, 0)
	}
}