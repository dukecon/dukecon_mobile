package org.dukecon.domain.repository

import org.dukecon.domain.model.Library

interface LibrariesRepository {
    suspend fun getLibraries(): List<Library>
}
