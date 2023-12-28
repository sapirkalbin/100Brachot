package com.axppress.hundredblessings

import android.app.Application
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.axppress.hundredblessings.di.viewModels

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(viewModels/*, usecases, remoteModel, localModule*/)
        }
    }
}
