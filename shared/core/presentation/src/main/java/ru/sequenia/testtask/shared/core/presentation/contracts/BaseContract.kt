package ru.sequenia.testtask.shared.core.presentation.contracts

import ru.sequenia.testtask.shared.core.presentation.dispatchers.EventsDispatcher

interface BaseContract {

	interface View<T> {

		fun bind(dispatcher: EventsDispatcher<T>)
	}

	interface Presenter<T> {

		fun onViewCreated(view: T)
	}
}