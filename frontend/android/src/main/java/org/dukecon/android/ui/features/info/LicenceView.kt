package org.dukecon.android.ui.features.info

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.dukecon.android.ui.ext.getAppComponent
import org.dukecon.presentation.feature.info.InfoContract
import org.dukecon.presentation.model.LibraryView
import javax.inject.Inject

class LicenceView(context: Context, attrs: AttributeSet? = null) : RecyclerView(context, attrs), InfoContract.View {
    override fun showError(throwable: Throwable) {

    }

    @Inject
    lateinit var presenter: InfoContract.Presenter

    private val adapter: InfoAdapter

    init {
        context.getAppComponent().infoComponent(InfoModule(context)).inject(this)

        layoutManager = LinearLayoutManager(context, VERTICAL, false)
        adapter = InfoAdapter { library: LibraryView ->
            presenter.onLibraryClicked(library)
        }
        super.setAdapter(adapter)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        presenter.onAttach(this)
    }

    override fun onDetachedFromWindow() {
        presenter.onDetach()
        super.onDetachedFromWindow()
    }

    override fun showLibraries(libraries: List<LibraryView>) {
        adapter.setLibraries(libraries)
        adapter.notifyDataSetChanged()
    }
}