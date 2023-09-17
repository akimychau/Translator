package com.example.translator.presentation.view.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.translator.data.data.DataModel
import com.example.translator.databinding.ItemTranscriptionBinding
import com.example.translator.utils.convertMeaningsToString

class MainAdapter : RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {

    private var data: List<DataModel> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class RecyclerItemViewHolder(private val viewBinding: ItemTranscriptionBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {

                viewBinding.headerTextviewRecyclerItem.text = data.text

                viewBinding.descriptionTextviewRecyclerItem.text =
                    convertMeaningsToString(data.meanings!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RecyclerItemViewHolder(
        ItemTranscriptionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }
}

