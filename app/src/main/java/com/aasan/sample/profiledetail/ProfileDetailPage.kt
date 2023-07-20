package com.aasan.sample.profiledetail



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.aasan.sample.R
import com.aasan.sample.base.AppNavigator
import com.aasan.sample.base.BaseFragment
import com.aasan.sample.categories.FirstCategoryPageFragment
import com.aasan.sample.categories.SecondCategoryPageFragment
import com.aasan.sample.databinding.FragmentProfileDetailPageBinding
import com.aasan.sample.databinding.FragmentProfilePageBinding
import com.aasan.sample.databinding.UiFragmentLoginBinding
import com.aasan.sample.profiledetail.adapter.StockViewPagerAdapter
import com.aasan.sample.splash.ChatAdapter
import com.aasan.sample.splash.SplashLandingViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileDetailPage : BaseFragment<SplashLandingViewModel>(){

    override var layoutId: Int = R.layout.fragment_profile_detail_page
// hr@codewinglet.com
  lateinit var viewPager : ViewPager2

    val viewModel: SplashLandingViewModel by viewModels()

    val profileDetailPageBinding : FragmentProfileDetailPageBinding by lazy {
        binding as FragmentProfileDetailPageBinding
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         super.onCreateView(inflater, container, savedInstanceState)

        return profileDetailPageBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
    }
    private fun initViewPager(){
        viewPager = profileDetailPageBinding.financialsViewPager
        val tabLayout : TabLayout = profileDetailPageBinding.financialsTabLayout

        val adapter = StockViewPagerAdapter(childFragmentManager,lifecycle)

        val fragmentList = listOf<Pair<Fragment,String>>(

            Pair(FirstCategoryPageFragment.newInstance(21),"First Tab"),
                    Pair(SecondCategoryPageFragment.newInstance(21),"Second Tab"))


        adapter.addFragments(fragmentList)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout,viewPager){tab,position->
            tab.text = adapter.getTabTitle(position)
        }.attach()



    }









}