package org.dukecon.presentation

interface BasePresenter<T : BaseView> {
    fun onAttach(view: T)
    fun onDetach()
    fun showError(error:Throwable)
}