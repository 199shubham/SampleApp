package com.aasan.sample.profile



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.aasan.sample.R
import com.aasan.sample.base.AppNavigator
import com.aasan.sample.base.BaseFragment
import com.aasan.sample.databinding.FragmentProfilePageBinding
import com.aasan.sample.databinding.UiFragmentLoginBinding
import com.aasan.sample.splash.ChatAdapter
import com.aasan.sample.splash.SplashLandingViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfilePageFragment : BaseFragment<SplashLandingViewModel>(),AppNavigator.SplashLandingNavigator{

    override var layoutId: Int = R.layout.fragment_profile_page



    val viewModel: SplashLandingViewModel by viewModels()

    val profileBinding : FragmentProfilePageBinding by lazy {
        binding as FragmentProfilePageBinding
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         super.onCreateView(inflater, container, savedInstanceState)
        viewModel.navigator = this
        profileBinding.vm = viewModel
        return profileBinding?.root
    }
    override fun onContinueClicked() {
     requireView().findNavController().navigate(ProfilePageFragmentDirections.actionProfilePageFragmentToCameraViewFragment())
    }







}