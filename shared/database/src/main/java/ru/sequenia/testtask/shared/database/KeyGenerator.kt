package ru.sequenia.testtask.shared.database

import java.util.concurrent.atomic.AtomicLong

class KeyGenerator {

	private val key = AtomicLong(0L)

	fun getNewKey() = key.getAndIncrement()
}