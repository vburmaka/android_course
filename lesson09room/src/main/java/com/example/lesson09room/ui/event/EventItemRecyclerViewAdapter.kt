package com.example.room.ui.event

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import com.example.lesson09room.databinding.FragmentItemBinding


import com.example.room.models.EventModel

class EventItemRecyclerViewAdapter(
    private val values: List<EventModel>,
    private val onEventClickListener: OnEventClickListener
) : RecyclerView.Adapter<EventItemRecyclerViewAdapter.EventHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventHolder {

        return EventHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        holder.bind(values[position])
    }

    override fun getItemCount(): Int = values.size

    inner class EventHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root), OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        private lateinit var event: EventModel

        private val idView: TextView = binding.eventItemIdTextView
        private val contentView: TextView = binding.dateTimeTextView

        fun bind(event: EventModel){
            this.event = event
            idView.text = event.title
            contentView.text = event.date.toString()
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }

        override fun onClick(view: View?) {
            onEventClickListener.onEventClicked(event.id, event.title)
        }
    }

}