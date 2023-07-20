package com.aasan.sample


import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aasan.sample.splash.ChatAdapter
import com.aasan.sample.splash.SampleAdapter
import com.aasan.sample.splash.data.QAListResponse
import com.aasan.sample.splash.data.SampleData



object Extension {
    @JvmStatic
    @BindingAdapter("sample_list_adapter")
    fun setSampleListData(view: RecyclerView?,list: SampleData?) {
        view?.adapter?.let { adapter ->
            adapter as SampleAdapter
            adapter.updateData(list?.entries ?: emptyList())
        }
    }



    @JvmStatic
    @BindingAdapter("load_profile_details")
    fun loadProfileDetails(view: RecyclerView?,list: List<QAListResponse>?) {
        view?.adapter?.let { adapter ->
            adapter as ChatAdapter
            adapter.updateData(list?.toList() ?: emptyList())
        }
    }
}