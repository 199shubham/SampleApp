package com.aasan.sample.login



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.aasan.sample.R
import com.aasan.sample.base.AppNavigator
import com.aasan.sample.base.BaseFragment
import com.aasan.sample.databinding.UiFragmentLoginBinding
import com.aasan.sample.splash.ChatAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginFragmentViewModel>(),AppNavigator.LoginInNavigator{

    override var layoutId: Int = R.layout.ui_fragment_login



    val viewModel: LoginFragmentViewModel by viewModels()

    val loginBinding : UiFragmentLoginBinding by lazy {
        binding as UiFragmentLoginBinding
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         super.onCreateView(inflater, container, savedInstanceState)
        viewModel.navigator = this
        loginBinding.vm = viewModel

        getSamplesData()

        loginBinding.recSample.adapter = ChatAdapter()

        return loginBinding?.root
    }

    fun getSamplesData(){
        viewModel.getCartStripData().observe(viewLifecycleOwner, Observer {
            it?.let {
            viewModel.profileDetails.set(it.qaListResponse)
        }

        })

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.workStatusData.collect {
                    return@collect
                   System.out.println("displayflowdata" + it)
                }
            }
        }

        lifecycleScope.launch {
            viewModel.users.observe(viewLifecycleOwner, { users ->
                System.out.println(users.body())
            })
        }
    }

    override fun onContinueClicked() {
     //  viewModel.getSampleData()
      //  viewModel.getDataWithFlow()
    requireView().findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
    }

    // hot flow and cold flow
}