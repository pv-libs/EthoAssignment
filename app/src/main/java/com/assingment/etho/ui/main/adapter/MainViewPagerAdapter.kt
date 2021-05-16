package com.assingment.etho.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.assingment.etho.ui.client.ClientHomeFragment
import com.assingment.etho.ui.server.ServerHomeFragment
import dagger.hilt.android.scopes.ActivityScoped
import timber.log.Timber
import javax.inject.Inject

@ActivityScoped
class MainViewPagerAdapter @Inject constructor(
    activity: FragmentActivity
) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        Timber.d("createFragment - $position")
        return when (position) {
            0 -> ServerHomeFragment()
            1 -> ClientHomeFragment()
            else -> throw IllegalStateException()
        }
    }
}