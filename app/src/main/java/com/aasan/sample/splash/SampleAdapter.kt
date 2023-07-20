package com.aasan.sample.splash

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aasan.sample.databinding.SampleListItemBinding


import com.aasan.sample.splash.data.Entry

class SampleAdapter(private val callback: SplashLandingViewModel):
    RecyclerView.Adapter<SampleAdapter.VH>() {
    private val sampleList = ArrayList<Entry>()
    fun updateData(list: List<Entry>){
        sampleList.clear()
        sampleList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            SampleListItemBinding.inflate(LayoutInflater.from(parent.context),
        parent, false), callback)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.setData(sampleList[position])
    }

    override fun getItemCount(): Int {
        return sampleList.size
    }

    inner class VH(private val sampleListItemBinding: SampleListItemBinding,
                   private val callback: SplashLandingViewModel):
        RecyclerView.ViewHolder(sampleListItemBinding.root) {
        fun setData(data: Entry){
            sampleListItemBinding.txtType.text = data.Link
        }
    }
}