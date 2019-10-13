package org.dukecon.android.ui.features.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.fragment_info.*
import org.dukecon.android.ui.R
import org.dukecon.android.ui.features.login.LoginFragment

class InfoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewpager.offscreenPageLimit = INFO_PAGES.size
        viewpager.adapter = InfoAdapter(childFragmentManager)
        tabs.setupWithViewPager(viewpager)
    }

    /**
     * Adapter that builds a page for each info screen.
     */
    inner class InfoAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getCount() = INFO_PAGES.size

        override fun getItem(position: Int) = INFO_PAGES[position]()

        override fun getPageTitle(position: Int): CharSequence {
            return resources.getString(INFO_TITLES[position])
        }
    }

    companion object {
        fun newInstance() = InfoFragment()

        private val TAG: String = InfoFragment::class.java.simpleName
        private val INFO_TITLES = arrayOf(
            R.string.login_title,
            R.string.licences_title
        )
        private val INFO_PAGES = arrayOf(
            { LoginFragment() },
            { LicenceFragment() }
        )
    }
}
