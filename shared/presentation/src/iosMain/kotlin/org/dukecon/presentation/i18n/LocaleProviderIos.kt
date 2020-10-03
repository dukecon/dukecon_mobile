package org.dukecon.presentation.i18n

import platform.Foundation.*

actual fun getLocalCode(): String = NSLocale.currentLocale().objectForKey(NSLocaleLanguageCode) as String
