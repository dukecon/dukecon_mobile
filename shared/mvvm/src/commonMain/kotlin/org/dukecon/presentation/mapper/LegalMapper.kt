package org.dukecon.presentation.mapper

import org.dukecon.domain.model.Event
import org.dukecon.domain.model.Library
import org.dukecon.presentation.model.LibraryView

/**
 * Map a [LibraryView] to and from a [Library] instance when data is moving between
 * this layer and the Domain layer
 */
open class LibraryMapper: Mapper<LibraryView, Library> {

    /**
     * Map a [Event] instance to a [LibraryView] instance
     */
    override fun mapToView(type: Library): LibraryView {
        return LibraryView(type.name, type.owner, type.license, type.url)
    }
}