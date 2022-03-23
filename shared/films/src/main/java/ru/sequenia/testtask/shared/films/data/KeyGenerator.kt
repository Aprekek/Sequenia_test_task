package ru.sequenia.testtask.shared.films.data

import java.util.concurrent.atomic.AtomicLong

class KeyGenerator {

	private val key = AtomicLong(0L)

	fun getNewKey() = key.getAndIncrement()
}