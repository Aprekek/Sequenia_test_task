package ru.sequenia.testtask.shared.database

import android.content.SharedPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicLong

class KeyGenerator(
	private val sharedPreferences: SharedPreferences
) {

	private companion object {

		const val KEY_TAG = "key_tag"
	}

	private val key = AtomicLong(0L)
	private val scope = CoroutineScope(Job() + Dispatchers.IO)

	fun getNewKey() = key.getAndIncrement()

	init {
		scope.launch {
			restoreLastKeyValue()
		}
	}

	private fun restoreLastKeyValue() {
		key.set(sharedPreferences.getLong(KEY_TAG, 0L))
	}

	fun saveKey() {
		sharedPreferences.edit().putLong(KEY_TAG, key.get()).apply()
	}

	fun reset() {
		key.set(0L)
	}
}