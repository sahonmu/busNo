package com.sahonmu.burger87.utils

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

object FragmentUtils {

    fun replaceFragmentNoBackstack(
        fm: FragmentManager?,
        newFragment: Fragment?, @IdRes containerId: Int
    ) {
        if (fm != null && newFragment != null) {
            fm.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(containerId, newFragment, newFragment.javaClass.simpleName)
                .commitAllowingStateLoss()
        }
    }
}
