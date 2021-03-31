package org.dukecon.domain.repository

import org.dukecon.domain.model.Library

interface LibrariesRepository {
  fun getLibraries(): List<Library>
}
