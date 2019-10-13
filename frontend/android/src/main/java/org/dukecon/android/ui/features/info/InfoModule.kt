package org.dukecon.android.ui.features.info

import android.content.Context
import dagger.Module
import dagger.Provides
import org.dukecon.android.ui.injection.injectors.InfoPresenterInj
import org.dukecon.domain.features.libraries.LibrariesListRepository
import org.dukecon.domain.repository.LibrariesRepository
import org.dukecon.presentation.feature.info.InfoContract
import org.dukecon.presentation.feature.info.InfoPresenter
import org.dukecon.presentation.feature.info.WebNavigator
import org.dukecon.presentation.mapper.LibraryMapper

@Module
class InfoModule(private val context: Context) {

    @Provides
    fun provideLibraryMapper(): LibraryMapper = LibraryMapper()

    @Provides
    fun libraryProvider(): LibrariesRepository {
        return LibrariesListRepository()
    }

    @Provides
    fun WebNavigator(): WebNavigator {
        return AndroidWebNavigator(context)
    }

    @Provides
    fun infoPresenter(impl: InfoPresenterInj
    ): InfoContract.Presenter {
        return impl
    }
}