package com.aasan.sample.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.aasan.sample.MainActivity
import com.aasan.sample.R
import com.aasan.sample.base.AppNavigator
import com.aasan.sample.base.BaseFragment
import com.aasan.sample.databinding.FragmentFirstCategoryBinding
import com.aasan.sample.databinding.FragmentHomeBinding
import com.aasan.sample.splash.SplashLandingViewModel
import dagger.hilt.android.AndroidEntryPoint



private const val ARG_PARAM1 = "param1"

private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class HomeFragment :BaseFragment<SplashLandingViewModel>() {

    private var param1 :String? = null

    private var param2 :String? = null

    override var layoutId: Int = R.layout.fragment_home

    val viewModel :SplashLandingViewModel by viewModels()

    val homeBinding :FragmentHomeBinding by lazy {
        binding as FragmentHomeBinding
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return homeBinding?.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = (childFragmentManager.findFragmentById(R.id.homeNavHost) as NavHostFragment).navController
        homeBinding.navBar.setupWithNavController(navController)
        (requireActivity() as MainActivity).navController = navController
    }

    companion object{
        private const val STOCK_ID = "stockId"
        fun newInstance(stockSymbol:Int?)=
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putInt(STOCK_ID,stockSymbol?:0)
                }
            }
    }




}