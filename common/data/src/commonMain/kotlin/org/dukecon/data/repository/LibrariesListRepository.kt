package org.dukecon.data.repository

import org.dukecon.domain.model.Library
import org.dukecon.domain.repository.LibrariesRepository

class LibrariesListRepository : LibrariesRepository {
    override suspend fun getLibraries(): List<Library> {
        return libraries
    }

    val libraries = listOf(
            Library("Dukecon Android", "Dukecon", "Apache 2", "https://github.com/dukecon/dukecon_android"),
            Library("Picasso", "Square", "Apache 2", "https://github.com/square/picasso"),
            Library("okhttp", "Square", "Apache 2", "https://github.com/square/okhttp"),
            Library("Chicago Roboto", "Ryan Harter", "Apache 2", "https://github.com/rharter/chicago-roboto"),
            Library("Android Clean Architecture Boilerplate", "Buffer inc", "MIT License", "https://github.com/bufferapp/android-clean-architecture-boilerplate"),
            Library("AOSP", "Android", "Apache 2", "https://android.googlesource.com"),
            Library("appcompat-v7", "Android", "Apache 2", "https://android.googlesource.com/platform/frameworks/support/+/master/v7/appcompat/"),
            Library("constraint-layout", "Android", "Android SDK", "https://developer.android.com/studio/terms.html"),
            Library("design", "Android", "Apache 2", "https://android.googlesource.com/platform/frameworks/support/+/master/design/"),
            Library("recyclerview-v7", "Android", "Apache 2", "https://android.googlesource.com/platform/frameworks/support/+/refs/heads/master/v7/recyclerview"),
            Library("Dagger 2", "Google", "Apache 2", "https://github.com/google/dagger"),
            Library("Kotlin", "JetBrains", "Apache 2", "http://kotlinlang.org/"),
            Library("CircularImageView", "Lopez Mikhael", "Apache 2", "https://github.com/lopspower/CircularImageView"),
            Library("GSON serialiser/deserialisers", "(c) 2014 Greg Kopff", "The MIT License (MIT)", "https://github.com/leokraemer/android-gson-javatime-serialisers")

    )

}