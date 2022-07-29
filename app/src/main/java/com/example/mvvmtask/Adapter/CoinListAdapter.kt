package com.example.mvvmtask.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmtask.Model.List1
import com.example.mvvmtask.databinding.CustomCoinLayoutBinding

class CoinListAdapter : RecyclerView.Adapter<CoinListAdapter.ViewHolder>  {
    private lateinit var binding: CustomCoinLayoutBinding
    var mContext: Context? = null
    private var data = ArrayList<List1>()

    constructor(
        mContext: Context?,
        data: ArrayList<List1   >
    ) : super() {
        this.mContext = mContext
        this.data = data
    }

    constructor() : super()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(mContext)
        binding = CustomCoinLayoutBinding.inflate(inflater, parent, false)

        return CoinListAdapter.ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (data.get(position).pictures != null) {
            Glide.with(mContext ?: return)
                .load(data.get(position).pictures.front.url)
                .into(binding.image)
        }

        binding.name.text = data.get(position).name
    }

    override fun getItemCount(): Int {
       return data.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

    }

}