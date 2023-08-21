package com.example.translator.presentation.view.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.translator.data.data.DataModel
import com.example.translator.databinding.ItemTranscriptionBinding

class MainAdapter(
    private var onListItemClickListener: OnListItemClickListener,
    private var data: List<DataModel>
) : RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {

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

//                viewBinding.descriptionTextviewRecyclerItem.text =
//                    data.meanings[FIRST_ITEM_INDEX].translation.text
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

    interface OnListItemClickListener {
        fun onItemClick(data: DataModel)
    }
}

