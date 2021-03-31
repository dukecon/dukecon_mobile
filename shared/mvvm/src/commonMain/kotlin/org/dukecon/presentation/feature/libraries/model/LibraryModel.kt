package org.dukecon.presentation.feature.libraries.model

data class Libraries(val libraries: List<LibraryData>)

data class LibraryData(
    val name: String = "",
    val owner: String = "",
    val license: String = "",
    val url: String = ""
)

@Suppress("unused") fun createDefaultLibraryData(): LibraryData = LibraryData()
