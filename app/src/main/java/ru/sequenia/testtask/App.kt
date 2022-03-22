package ru.sequenia.testtask

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.sequenia.testtask.di.appModule
import ru.sequenia.testtask.di.globalNavigationModule
import ru.sequenia.testtask.di.routersModule
import ru.sequenia.testtask.features.films.general.di.filmsGeneralModule
import ru.sequenia.testtask.network.di.networkModule
import ru.sequenia.testtask.shared.database.di.databaseModule

class App : Application() {

	override fun onCreate() {
		super.onCreate()

		startKoin {
			androidContext(this@App)

			modules(
				globalNavigationModule,
				routersModule,
				networkModule,
				databaseModule,
				filmsGeneralModule,
			)
		}
	}
}