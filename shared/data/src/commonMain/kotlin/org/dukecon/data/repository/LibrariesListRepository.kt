package org.dukecon.data.repository

import org.dukecon.domain.model.Library
import org.dukecon.domain.repository.LibrariesRepository
import org.dukecon.platform.HostPlatform

class LibrariesListRepository : LibrariesRepository {
    override fun getLibraries(): List<Library> {
        return libraries
    }

    private val libraries = listOf(
        Library(
            "Dukecon Android",
            "Dukecon",
            "Apache 2",
            "https://github.com/dukecon/dukecon_android"
        ),
        Library("okhttp", "Square", "Apache 2", "https://github.com/square/okhttp"),
        Library(
            "Chicago Roboto",
            "Ryan Harter",
            "Apache 2",
            "https://github.com/rharter/chicago-roboto"
        ),
        Library("AOSP", "Android", "Apache 2", "https://android.googlesource.com"),
        Library(
            "appcompat-v7",
            "Android",
            "Apache 2",
            "https://android.googlesource.com/platform/frameworks/support/+/master/v7/appcompat/"
        ),
        Library(
            "constraint-layout",
            "Android",
            "Android SDK",
            "https://developer.android.com/studio/terms.html"
        ),
        Library(
            "design",
            "Android",
            "Apache 2",
            "https://android.googlesource.com/platform/frameworks/support/+/master/design/"
        ),
        Library(
            "recyclerview-v7",
            "Android",
            "Apache 2",
            "https://android.googlesource.com/platform/frameworks/support/+/refs/heads/master/v7/recyclerview"
        ),
        Library("Kotlin", "JetBrains", "Apache 2", "http://kotlinlang.org/", HostPlatform.COMMON),
        Library(
            "Kotlin/Native",
            "JetBrains",
            "Apache 2",
            "http://kotlinlang.org/",
            HostPlatform.COMMON
        ),
        Library("Ktor", "JetBrains", "Apache 2", "https://ktor.io", HostPlatform.COMMON),
        Library(
            "CircularImageView",
            "Lopez Mikhael",
            "Apache 2",
            "https://github.com/lopspower/CircularImageView"
        )
    )
}