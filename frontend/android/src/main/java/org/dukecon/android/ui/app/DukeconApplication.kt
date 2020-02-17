package org.dukecon.android.ui.app

//import androidx.multidex.MultiDex

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.classic.android.LogcatAppender
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import org.dukecon.cache.storage.appAndroidContext
import org.slf4j.LoggerFactory

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
        val lc = LoggerFactory.getILoggerFactory() as LoggerContext
        lc.stop()

        // setup LogcatAppender


        // setup LogcatAppender
        // setup LogcatAppender
        val logcatEncoder = PatternLayoutEncoder()
        logcatEncoder.context = lc
        logcatEncoder.pattern = "[%thread] %msg%n"
        logcatEncoder.start()

        val logcatAppender = LogcatAppender()
        logcatAppender.context = lc
        logcatAppender.encoder = logcatEncoder
        logcatAppender.start()

        // add the newly created appenders to the root logger;
        // qualify Logger to disambiguate from org.slf4j.Logger


        // add the newly created appenders to the root logger;
        // qualify Logger to disambiguate from org.slf4j.Logger
        // add the newly created appenders to the root logger;
        // qualify Logger to disambiguate from org.slf4j.Logger
        val root = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME) as Logger
        root.addAppender(logcatAppender)

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
