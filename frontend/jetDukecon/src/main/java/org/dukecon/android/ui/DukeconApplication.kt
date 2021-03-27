package org.dukecon.android.ui

import android.app.Application
import org.dukecon.android.IoCRegister

class DukeconApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        IoCRegister.registerTimeProvider()
        IoCRegister.registerEventDetail(this)
        initLogger()
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            //FileAppenderLogger().setup(this)
        }
    }

}