package com.aasan.sample.profiledetail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class StockViewPagerAdapter (fragmentManager:FragmentManager,lifecycle :Lifecycle):
FragmentStateAdapter(fragmentManager,lifecycle){

    var pages  = mutableListOf<Pair<Fragment,String>>()

    fun addFragments(fragments :List<Pair<Fragment,String>>){
        pages.addAll(fragments)
    }

    override fun getItemCount(): Int =pages.size

    override fun createFragment(position: Int): Fragment = pages[position].first

    fun getTabTitle(position: Int) :String = pages[position].second
}