package org.dukecon.android.ui.app

//import androidx.multidex.MultiDex

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import org.dukecon.android.ui.BuildConfig
import org.dukecon.android.ui.app.aspect.logging.FileAppenderLogger
import org.dukecon.cache.storage.appAndroidContext

class DukeconApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appAndroidContext = this
        IoCRegister.registerTimeProvider()
        IoCRegister.registerEventDetail(this)
        IoCRegister.registerSpeakerDetail(this)
        initLogger()
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            FileAppenderLogger().setup(this)
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
