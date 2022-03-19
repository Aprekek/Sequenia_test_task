package ru.sequenia.testtask.shared.core.presentation.dispatchers

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import java.util.LinkedList

class EventsDispatcher<T> : DefaultLifecycleObserver {

	private var eventsListener: T? = null
	private var activeListener: T? = null
	private var eventsList = LinkedList<T.() -> Unit>()

	fun bind(lifecycleOwner: LifecycleOwner, listener: T) {
		eventsListener = listener
		lifecycleOwner.lifecycle.addObserver(this)
	}

	override fun onResume(owner: LifecycleOwner) {
		connectListener()
	}

	override fun onPause(owner: LifecycleOwner) {
		disconnectListener()
	}

	override fun onDestroy(owner: LifecycleOwner) {
		clear(owner)
	}

	private fun connectListener() {
		activeListener = eventsListener
		activeListener?.let { listener ->
			eventsList.forEach { event ->
				event(listener)
			}
			eventsList.clear()
		}
	}

	private fun disconnectListener() {
		activeListener = null
	}

	private fun clear(lifecycleOwner: LifecycleOwner) {
		eventsListener = null
		activeListener = null
		lifecycleOwner.lifecycle.removeObserver(this)
	}

	fun dispatchEvent(event: T.() -> Unit) {
		activeListener?.also(event) ?: eventsList.add(event)
	}
}