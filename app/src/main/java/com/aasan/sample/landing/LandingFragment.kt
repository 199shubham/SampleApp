package com.aasan.sample.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.aasan.sample.R
import com.aasan.sample.base.AppNavigator
import com.aasan.sample.base.BaseFragment
import com.aasan.sample.databinding.FragmentLandingBinding
import com.aasan.sample.splash.SplashLandingFragmentDirections
import com.aasan.sample.splash.SplashLandingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LandingFragment : BaseFragment<SplashLandingViewModel>(),AppNavigator.SplashLandingNavigator {

    private var param1 :String? = null

    private var param2 :String? = null

    override var layoutId: Int = R.layout.fragment_landing

    val viewModel :SplashLandingViewModel by viewModels()

    val landingBinding : FragmentLandingBinding by lazy {
        binding as FragmentLandingBinding
    }




    companion object{
        private const val STOCK_ID = "stockId"
        fun newInstance(stockSymbol:Int?)=
            LandingFragment().apply {
                arguments = Bundle().apply {
                    putInt(STOCK_ID,stockSymbol?:0)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
        viewModel.navigator = this

        return landingBinding?.root
    }

    override fun onContinueClicked() {
      //  viewModel.fetchData()
        requireView().findNavController().navigate(SplashLandingFragmentDirections.actionSplashLandingFragmentToLoginFragment())
    }


}