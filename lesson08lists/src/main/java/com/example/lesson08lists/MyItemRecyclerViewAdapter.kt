package com.example.lesson08lists

import android.telecom.Call.Details
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import com.example.lesson08lists.placeholder.PlaceholderContent.PlaceholderItem
import com.example.lesson08lists.databinding.ItemFragmentListExampleBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyItemRecyclerViewAdapter(
    private val values: List<PlaceholderItem>,
    private val onItemClickListener: AdapterOnItemClickListener
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    private var counter = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFragmentListExampleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id
        holder.contentView.text = item.content

        Log.e("TAG", "onBindViewHolder: ${item.id}", )
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: ItemFragmentListExampleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init{
            binding.root.setOnClickListener {
                onItemClickListener.onItemClicked(values[adapterPosition].id, values[adapterPosition].details)
            }
            Log.w("TAG", "ViewHolder: $counter" )
            counter++
        }
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    interface AdapterOnItemClickListener{
        fun onItemClicked(id: String, details: String)
    }
}