package org.dukecon.android.ui.features.login.di

import dagger.Subcomponent
import org.dukecon.android.ui.features.login.LoginFragment

@Subcomponent(modules = arrayOf(LoginModule::class))
interface LoginComponent {
    fun inject(loginFragment: LoginFragment)
}