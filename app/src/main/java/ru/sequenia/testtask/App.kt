package ru.sequenia.testtask

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.sequenia.testtask.di.globalNavigationModule
import ru.sequenia.testtask.network.di.networkModule
import ru.sequenia.testtask.shared.database.di.databaseModule

class App : Application() {

	override fun onCreate() {
		super.onCreate()

		startKoin {
			androidContext(this@App)

			modules(
				globalNavigationModule,
				networkModule,
				databaseModule,
			)
		}
	}
}