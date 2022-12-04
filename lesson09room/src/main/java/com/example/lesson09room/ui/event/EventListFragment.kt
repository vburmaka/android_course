package com.example.room.ui.event

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.lesson09room.R
import com.example.room.core.EventListRepositoryViewModelFactory
import com.example.room.core.EventsApplication
import com.example.room.models.EventModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

/**
 * A fragment representing a list of Items.
 */
class EventListFragment : Fragment(), OnEventClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyScreenTextView: TextView
    private var onEvenSelectedListener: OnEvenSelectedListener? = null

    private val eventListViewModel: EventListViewModel by viewModels {
        EventListRepositoryViewModelFactory((requireActivity().application as EventsApplication).repository)
    }

    private var addEventFAB: FloatingActionButton? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onEvenSelectedListener = context as? OnEvenSelectedListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        recyclerView = view.findViewById(R.id.newList)
        emptyScreenTextView = view.findViewById(R.id.emptyScreenTextView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(DividerItemDecoration(context,VERTICAL))
        addEventFAB = view.findViewById(R.id.addEventFAB)

        addEventFAB?.setOnClickListener {
            onEventClicked(UUID.randomUUID(),"")
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventListViewModel.eventsLiveData.observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun updateUI(events: List<EventModel>){
        if (events.isEmpty()){
            recyclerView.isVisible = false
            emptyScreenTextView.isVisible = true
        } else {
            recyclerView.isVisible = true
            emptyScreenTextView.isVisible = false
        }

        // TODO create fun updateEvents in EventItemRecyclerViewAdapter
        recyclerView.adapter = EventItemRecyclerViewAdapter(events, this)
    }

    override fun onDetach() {
        super.onDetach()
        onEvenSelectedListener = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = EventListFragment()
    }

    override fun onEventClicked(id: UUID, title: String) {
        onEvenSelectedListener?.onEventSelected(id.toString(), title)
    }
}