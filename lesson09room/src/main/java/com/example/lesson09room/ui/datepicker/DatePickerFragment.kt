package com.example.room.ui.datepicker

import android.app.DatePickerDialog
import android.app.Dialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import java.util.*

private const val ARG_DATE = "date"

class DatePickerFragment : DialogFragment() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dateListener = DatePickerDialog.OnDateSetListener {
            _: DatePicker, year: Int, month: Int, day: Int ->
            val resultDate: Date = GregorianCalendar(year, month, day).time

            parentFragment?.let {
                (it as OnDateSelectedListener).onDateSelected(resultDate)
            }
        }
        val date = arguments?.getSerializable(ARG_DATE) as Date?

        val calendar = Calendar.getInstance()
        calendar.time = date
        val initialYear = calendar.get(Calendar.YEAR)
        val initialMonth = calendar.get(Calendar.MONTH)
        val initialDay = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(
            requireContext(),
            dateListener,
            initialYear,
            initialMonth,
            initialDay
        )
    }

    companion object {
        @JvmStatic
        fun newInstance(date: Date) =
            DatePickerFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_DATE, date)
                }
            }
    }
}