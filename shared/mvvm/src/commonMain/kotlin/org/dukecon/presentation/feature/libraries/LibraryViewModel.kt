package org.dukecon.presentation.feature.libraries

import org.dukecon.aspects.logging.LogLevel
import org.dukecon.aspects.logging.log
import org.dukecon.domain.model.Library
import org.dukecon.domain.repository.LibrariesRepository
import org.dukecon.platform.HostPlatform
import org.dukecon.platform.getTargetHost
import org.dukecon.common.presentation.CommonViewModel
import org.dukecon.presentation.feature.libraries.model.Libraries
import org.dukecon.presentation.feature.libraries.model.LibraryData
import kotlin.native.concurrent.ThreadLocal

class LibraryViewModel(librariesRepository: LibrariesRepository) : CommonViewModel() {

    @Suppress("MemberVisibilityCanBePrivate")
    val libraries: Libraries

    init {
        log(LogLevel.DEBUG, "LibraryViewModel", "init")
        libraries = Libraries(
            librariesRepository.getLibraries()
                .filter { (it.targetHost == HostPlatform.COMMON) || (it.targetHost == getTargetHost()) }
                .map {
                    print(it.targetHost)
                    toLibraryData(it)
                }
        )
    }

    private fun toLibraryData(it: Library): LibraryData =
        LibraryData(
            name = it.name,
            license = it.license,
            owner = it.owner,
            url = it.url
        )

    @ThreadLocal
    companion object {
        @Suppress("unused")
        fun create(librariesRepository: LibrariesRepository) = LibraryViewModel(librariesRepository)
    }
}

