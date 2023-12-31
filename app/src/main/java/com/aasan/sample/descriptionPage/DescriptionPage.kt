package com.aasan.sample.descriptionPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
class DescriptionPage : Fragment() {

    private var param1 :String? = null

    private var param2 :String? = null




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
            DescriptionPage().apply {
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
        return inflater.inflate(R.layout.fragment_description_page,container,false)


    }



}