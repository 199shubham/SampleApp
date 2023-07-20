package com.aasan.sample.leaderly

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.aasan.sample.R
import com.aasan.sample.base.AppNavigator
import com.aasan.sample.base.BaseFragment
import com.aasan.sample.databinding.FragmentLeaderRlyBinding
import com.aasan.sample.databinding.UiFragmentLoginBinding
import com.aasan.sample.login.LoginFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LeaderRly.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class LeaderRly : BaseFragment<LeaderRlyViewModel>(), AppNavigator.LeaderRlyNavigator {

    override var layoutId: Int = R.layout.fragment_leader_rly

    val viewModel: LeaderRlyViewModel by viewModels()

    val leaderRlyBinding : FragmentLeaderRlyBinding by lazy {
        binding as FragmentLeaderRlyBinding
    }

    private var param1: String? = null
    private var param2: String? = null

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
        viewModel.navigator = this

        return leaderRlyBinding?.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LeaderRly().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onContinue() {
        System.out.println("shubhamsharma")
    }
}