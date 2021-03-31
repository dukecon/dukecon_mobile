package org.dukecon.android.ui.app.aspect.logging

import android.content.Context
import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.classic.android.LogcatAppender
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.Appender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy
import ch.qos.logback.core.util.FileSize
import org.slf4j.LoggerFactory

class FileAppenderLogger {
  fun setup(context: Context) {
    val loggerContext = LoggerFactory.getILoggerFactory() as LoggerContext
    loggerContext.stop()
    val rootLogger = LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME) as Logger
    rootLogger.addAppender(startLogcat(loggerContext))
    startSdCardLogger(context, loggerContext)?.let { rootLogger.addAppender(it) }
  }

  private fun startSdCardLogger(appContext: Context, lc: LoggerContext): Appender<ILoggingEvent>? {
    // null value represents ROOT dir [Context.getExternalFilesDir]
    appContext.getExternalFilesDir(null)?.let { logDir ->
      val rollingFileAppender =
          RollingFileAppender<ILoggingEvent>().apply {
            isAppend = true
            context = lc
          }
      val PERIODICITY = "yyyy-MM-dd"
      SizeAndTimeBasedRollingPolicy<ILoggingEvent>().apply {
        fileNamePattern = (logDir.absolutePath + "/log.%d{" + PERIODICITY + "}.%i.log.gz")
        setMaxFileSize(FileSize(FileSize.MB_COEFFICIENT * 20))
        maxHistory = 5 // keep logs for 5 days
        setParent(rollingFileAppender)
        context = lc
        start()
        rollingFileAppender.rollingPolicy = this
      }
      PatternLayoutEncoder().apply {
        pattern = "%date{ISO8601} [%thread] %-5level %logger{36} - %msg%n"
        context = lc
        start()
        rollingFileAppender.encoder = this
        rollingFileAppender.start()
      }
      return rollingFileAppender
    }
    return null
  }

  private fun startLogcat(
      loggerContext: LoggerContext
  ): Appender<ILoggingEvent> { // setup LogcatAppender
    val logcatEncoder =
        PatternLayoutEncoder().apply {
          context = loggerContext
          pattern = "[%thread] %msg%n"
          start()
        }
    return LogcatAppender().apply {
      context = loggerContext
      encoder = logcatEncoder
      start()
    }
  }
}
