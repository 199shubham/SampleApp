package com.aasan.sample.splash

import android.os.Bundle
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.aasan.sample.R


import com.aasan.sample.base.AppNavigator
import com.aasan.sample.base.BaseFragment
import com.aasan.sample.databinding.FragmentSampleDetailsBinding


import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashLandingFragment  : BaseFragment<SplashLandingViewModel>(), AppNavigator.SplashLandingNavigator{

    override var layoutId: Int = R.layout.fragment_sample_details
    val viewModel: SplashLandingViewModel by activityViewModels()
    private lateinit var sampleAdapter: SampleAdapter

    val sampleBinding: FragmentSampleDetailsBinding by lazy {
        binding as FragmentSampleDetailsBinding
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        viewModel.navigator = this
        sampleBinding.vm = viewModel
        return sampleBinding?.root
    }

    override fun onContinueClicked() {
     requireView().findNavController().navigate(SplashLandingFragmentDirections.actionSplashLandingFragmentToLoginFragment())
    }


}