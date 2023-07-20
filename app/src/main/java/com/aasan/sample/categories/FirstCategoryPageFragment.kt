package com.aasan.sample.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.aasan.sample.R
import com.aasan.sample.base.AppNavigator
import com.aasan.sample.base.BaseFragment
import com.aasan.sample.databinding.FragmentFirstCategoryBinding
import com.aasan.sample.splash.SplashLandingViewModel
import dagger.hilt.android.AndroidEntryPoint



private const val ARG_PARAM1 = "param1"

private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class FirstCategoryPageFragment : BaseFragment<SplashLandingViewModel>(),AppNavigator.SplashLandingNavigator {

    private var param1 :String? = null

    private var param2 :String? = null

    override var layoutId: Int = R.layout.fragment_first_category

    val viewModel :SplashLandingViewModel by viewModels()

    val profileBinding :FragmentFirstCategoryBinding by lazy {
        binding as FragmentFirstCategoryBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    companion object{
        private const val STOCK_ID = "stockId"
        fun newInstance(stockSymbol:Int?)=
            FirstCategoryPageFragment().apply {
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
         super.onCreateView(inflater, container, savedInstanceState)
        viewModel.navigator = this
        profileBinding.vm = viewModel
        return profileBinding?.root
    }

    override fun onContinueClicked() {

    }

}