package org.dukecon.presentation.feature.info

import kotlinx.coroutines.launch
import org.dukecon.domain.repository.LibrariesRepository
import org.dukecon.presentation.CoroutinePresenter
import org.dukecon.presentation.mapper.LibraryMapper
import org.dukecon.presentation.model.LibraryView

open class InfoPresenter constructor(
        private val repository: LibrariesRepository,
        private val libraryMapper: LibraryMapper,
        private val webNavigator: WebNavigator) : CoroutinePresenter<InfoContract.View>(), InfoContract.Presenter {

    override fun showError(error: Throwable) {

    }

    private var view: InfoContract.View? = null

    override fun onAttach(view: InfoContract.View) {
        this.view = view
        launch {
            val libraries = repository.getLibraries()
            view.showLibraries(libraries.map { libraryMapper.mapToView(it) })
        }
    }

    override fun onDetach() {
        this.view = null
    }

    override fun onLibraryClicked(library: LibraryView) {
        webNavigator.navigateToUrl(library.url)
    }

}

