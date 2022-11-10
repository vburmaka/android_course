package com.example.lesson08lists

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.lesson08lists.placeholder.PlaceholderContent

/**
 * The RecyclerView using example
 * Docs: https://developer.android.com/develop/ui/views/layout/recyclerview
 *
 * Other examples
 * https://guides.codepath.com/android/using-the-recyclerview
 * https://droidbyme.medium.com/android-recyclerview-with-multiple-view-type-multiple-view-holder-af798458763b
 */
class ExampleListFragment : Fragment(), MyItemRecyclerViewAdapter.AdapterOnItemClickListener {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_example, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MyItemRecyclerViewAdapter(PlaceholderContent.ITEMS, this@ExampleListFragment)
                addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            }
        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"
        const val TAG = "ExampleListFragment"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ExampleListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }

    override fun onItemClicked(id: String, details: String) {
        Log.e(TAG, "onItemClicked: id = $id, details = $details")
        Toast.makeText(activity, "Clicked $id, $details", Toast.LENGTH_LONG).show()
    }
}