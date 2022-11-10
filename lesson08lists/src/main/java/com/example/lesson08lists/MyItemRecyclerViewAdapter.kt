package com.example.lesson08lists

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

import com.example.lesson08lists.placeholder.PlaceholderContent.PlaceholderItem
import com.example.lesson08lists.databinding.ItemFragmentListExampleBinding
import com.example.lesson08lists.databinding.ItemWithButtonBinding
import com.example.lesson08lists.databinding.ItemWithCheckboxBinding
import com.example.lesson08lists.placeholder.PlaceholderContent

private const val SIMPLE_ITEM_VIEW_TYPE = 0
private const val CHECKBOX_ITEM_VIEW_TYPE = 1
private const val REMOVE_BUTTON_ITEM_VIEW_TYPE = 2
private const val ADD_BUTTON_ITEM_VIEW_TYPE = 3


/**
 * Implement adapter
 * https://developer.android.com/develop/ui/views/layout/recyclerview#implement-adapter
 */
class MyItemRecyclerViewAdapter(
    private val values: List<PlaceholderItem>,
    private val onItemClickListener: AdapterOnItemClickListener
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.BaseViewHolder>() {
    private var counter = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            SIMPLE_ITEM_VIEW_TYPE -> SimpleViewHolder(
                ItemFragmentListExampleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            CHECKBOX_ITEM_VIEW_TYPE -> CheckBoxViewHolder(
                ItemWithCheckboxBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            REMOVE_BUTTON_ITEM_VIEW_TYPE,
            ADD_BUTTON_ITEM_VIEW_TYPE -> ButtonViewHolder(
                ItemWithButtonBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else ->  SimpleViewHolder(
                ItemFragmentListExampleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = values[position]
        holder.bind(position,  values[position])

        Log.e("TAG", "onBindViewHolder: ${item.id}", )
    }

    override fun getItemCount(): Int = values.size

    override fun getItemViewType(position: Int): Int {
        return when (values[position]){
            is PlaceholderContent.SimpleItem -> SIMPLE_ITEM_VIEW_TYPE
            is PlaceholderContent.CheckBoxItem -> CHECKBOX_ITEM_VIEW_TYPE
            is PlaceholderContent.RemoveItem -> REMOVE_BUTTON_ITEM_VIEW_TYPE
            is PlaceholderContent.AddItem -> ADD_BUTTON_ITEM_VIEW_TYPE
            else -> SIMPLE_ITEM_VIEW_TYPE
        }
    }

    inner abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(position: Int, item: PlaceholderItem)
    }

    inner class SimpleViewHolder(binding: ItemFragmentListExampleBinding) :
        BaseViewHolder(binding.root) {
        init{
            binding.root.setOnClickListener {
                onItemClickListener.onItemClicked(values[adapterPosition].id, values[adapterPosition].details)
            }
            Log.w("TAG", "ViewHolder: $counter" )
            counter++
        }
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        override fun bind(position: Int, item: PlaceholderItem) {
            idView.text = item.id
            contentView.text = item.content
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    inner class CheckBoxViewHolder(binding: ItemWithCheckboxBinding) : BaseViewHolder(binding.root) {
        init{
            binding.root.setOnClickListener {
                onItemClickListener.onItemClicked(values[adapterPosition].id, values[adapterPosition].details)
            }
            Log.w("TAG", "ViewHolder: $counter" )
            counter++
        }
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        override fun bind(position: Int, item: PlaceholderItem) {
            idView.text = item.id
            contentView.text = item.content
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    inner class ButtonViewHolder(binding: ItemWithButtonBinding) : BaseViewHolder(binding.root) {
        init{
            binding.root.setOnClickListener {
                onItemClickListener.onItemClicked(values[adapterPosition].id, values[adapterPosition].details)
            }
            Log.w("TAG", "ViewHolder: $counter" )
            counter++
        }
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content
        val button: Button = binding.itemButton

        override fun bind(position: Int, item: PlaceholderItem) {
            idView.text = item.id
            contentView.text = item.content
            button.text = if (item is PlaceholderContent.RemoveItem) "Remove Item" else "Add Item"
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    interface AdapterOnItemClickListener{
        fun onItemClicked(id: String, details: String)
    }
}