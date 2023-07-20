package com.aasan.sample.splash

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aasan.sample.databinding.SampleListItemBinding


import com.aasan.sample.splash.data.Entry
import com.aasan.sample.splash.data.QAListResponse

class ChatAdapter: RecyclerView.Adapter<ChatAdapter.VH>() {


    private val chatDetails = ArrayList<QAListResponse>()


    fun updateData(list: List<QAListResponse>){
        chatDetails.clear()
        chatDetails.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            SampleListItemBinding.inflate(LayoutInflater.from(parent.context),
        parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.setData(chatDetails[position])
    }

    override fun getItemCount(): Int {
        return chatDetails.size
    }

    inner class VH(private val homeListItem: SampleListItemBinding):
        RecyclerView.ViewHolder(homeListItem.root) {
        fun setData(data: QAListResponse){
            homeListItem.item = data
            homeListItem.txtType.text = data.question
            homeListItem.txtType1.text = data.answer
        }
    }
}