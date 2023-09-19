package com.jefflete.crazyproducts

import android.app.Application
import com.jefflete.crazyproducts.core.di.databaseModule
import com.jefflete.crazyproducts.core.di.networkModule
import com.jefflete.crazyproducts.core.di.repositoryModule
import com.jefflete.crazyproducts.di.useCaseModule
import com.jefflete.crazyproducts.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ProductApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@ProductApp)
            modules(
                listOf(
                    databaseModule, networkModule, repositoryModule, useCaseModule, viewModelModule,
                )
            )
        }
    }
}