package org.dukecon.presentation.feature.info

import org.dukecon.presentation.BasePresenter
import org.dukecon.presentation.BaseView
import org.dukecon.presentation.model.LibraryView

interface InfoContract {

    interface View : BaseView {
        fun showLibraries(libraries: List<LibraryView>)
    }

    interface Presenter : BasePresenter<View> {
        fun onLibraryClicked(library: LibraryView)
    }
}