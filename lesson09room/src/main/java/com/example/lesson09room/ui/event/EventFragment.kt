package com.example.room.ui.event

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.example.lesson09room.R
import com.example.room.core.EventRepositoryViewModelFactory
import com.example.room.core.EventsApplication
import com.example.room.models.EventModel
import com.example.room.ui.datepicker.DatePickerFragment
import com.example.room.ui.datepicker.OnDateSelectedListener
import java.util.*

private const val ARG_PARAM_ID = "param1"
private const val ARG_PARAM_TITLE = "param2"
private const val DIALOG_DATE = "DialogDate"

class EventFragment : Fragment(), OnDateSelectedListener {
    private var event = EventModel()

    private lateinit var titleEditText: EditText
    private lateinit var dateButton: Button
    private lateinit var saveButton: Button
    private lateinit var shareButton: Button

    private var id: String? = null
    private var title: String? = null

    private val eventViewModel: EventViewModel by viewModels {
        EventRepositoryViewModelFactory((requireActivity().application as EventsApplication).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString(ARG_PARAM_ID)
            title = it.getString(ARG_PARAM_TITLE)
        }
    }

    private fun updateEventById(id: String?) {
        id?.let {
            eventViewModel.getEvenLiveData(UUID.fromString(it))
                .observe(viewLifecycleOwner) { eventModel ->
                    eventModel?.let {
                        event = it
                    }
                }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        updateEventById(id)

        val view: View = inflater.inflate(R.layout.fragment_event, container, false)

        titleEditText = view.findViewById(R.id.eventTitleEditText)
        dateButton = view.findViewById(R.id.eventDateButton)
        saveButton = view.findViewById(R.id.saveEventButton)
        shareButton = view.findViewById(R.id.shareEventButton)

        titleEditText.setText(title, TextView.BufferType.NORMAL)

        dateButton.setOnClickListener(object : OnClickListener {
            override fun onClick(p0: View?) {
                DatePickerFragment.newInstance(event.date).apply {
                    show(this@EventFragment.childFragmentManager, DIALOG_DATE)
                }
            }
        })

        saveButton.setOnClickListener {
            event.title = titleEditText.text.toString()
            eventViewModel.updateEvent(event)
            activity?.onBackPressed()
        }

        shareButton.setOnClickListener {

            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, event.title)
            }

            startActivity(intent)
        }

        return view
    }

    companion object {
        @JvmStatic

        fun newInstance(id: String, title: String): EventFragment {
            return EventFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM_ID, id)
                    putString(ARG_PARAM_TITLE, title)
                }
            }
        }
    }

    override fun onDateSelected(date: Date) {
        saveDate(date)
        updateUI()
    }

    private fun updateUI() {
//        TODO fix empty title after saveButton tapped
        titleEditText.setText(event.title, TextView.BufferType.NORMAL)
        dateButton.text = event.date.toString()
    }

    private fun saveDate(date: Date) {
        event.date = date
    }
}