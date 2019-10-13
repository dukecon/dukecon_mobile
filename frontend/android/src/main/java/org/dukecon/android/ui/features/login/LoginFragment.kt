package org.dukecon.android.ui.features.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.dukecon.android.ui.R
import org.dukecon.android.ui.ext.getComponent
import org.dukecon.android.ui.features.main.MainComponent
import org.dukecon.domain.aspects.auth.AuthManager
import org.dukecon.domain.features.oauth.TokensStorage
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class LoginFragment : Fragment(), CoroutineScope {

    private lateinit var mJob: Job
    override val coroutineContext: CoroutineContext
        get() = mJob + Dispatchers.Main

    @Inject
    lateinit var authManager: AuthManager

    @Inject
    lateinit var tokensStorage: TokensStorage

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.getComponent<MainComponent>()?.loginComponent()?.inject(this)
        mJob = Job()

        val token = tokensStorage.getToken()

        hasSession = authManager.hasSession(token)
        login_button.text = context?.getText(if (hasSession) R.string.logout else R.string.login)
        login_button.visibility = VISIBLE

        login_button.setOnClickListener {
            if (hasSession) {
                tokensStorage.clear()
            } else {
                launch {
                    authManager.login(activity as Any)
                }
            }
        }
    }

    private var hasSession: Boolean = false

    /*
    inner class GetTokenObserver : DisposableSingleObserver<OAuthToken>() {
        override fun onSuccess(token: OAuthToken) {
            login_button.visibility = VISIBLE
            hasSession = authManager.hasSession(token)
            login_button.text = context?.getText(if (hasSession) R.string.logout else R.string.login)
        }

        override fun onError(e: Throwable) {
            login_button.visibility = VISIBLE
            hasSession = false;
            login_button.text = context?.getText(R.string.login)
        }
    }

    inner class CleanTokenFromStorageObserver : DisposableSingleObserver<Any>() {
        override fun onSuccess(t: Any) {
            hasSession = false;
            login_button.text = context?.getText(R.string.login)
        }

        override fun onError(e: Throwable) {
            login_button.text = context?.getText(R.string.login)
        }
    }
    */
}

