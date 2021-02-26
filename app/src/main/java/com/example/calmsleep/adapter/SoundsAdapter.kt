package com.example.calmsleep.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.calmsleep.fragment.DownloadFragment
import com.example.calmsleep.fragment.FavouritesFragment
import com.example.calmsleep.fragment.RecentlyFragment
import com.example.calmsleep.fragment.ViewAllFragment

class SoundsAdapter: FragmentPagerAdapter{
    constructor(fm: FragmentManager) : super(
        fm,
        FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    )

    override fun getItem(position: Int): Fragment {

       return when (position) {
           0 -> {
               ViewAllFragment()
           }
           1 -> {
               FavouritesFragment()
           }
           else -> {
               DownloadFragment()
           }
        }
    }

    override fun getCount() = 3

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> {
                "Recently Listened"
            }
            1 -> {
                "Favourite"
            }
            else -> {
                "Download"
            }
        }
    }
}