package org.dukecon.android.ui.ext

import android.content.Context
import org.dukecon.android.ui.injection.ApplicationComponent

val SERVICE_COMPONENT = "component"

fun <T> Context.getComponent(): T {
    @Suppress("UNCHECKED_CAST")
    return this.getSystemService(SERVICE_COMPONENT) as T
}

fun Context.getAppComponent(): ApplicationComponent {
    return this.applicationContext.getSystemService(SERVICE_COMPONENT) as ApplicationComponent
}
