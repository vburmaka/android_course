package com.example.lesson05fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val secondFragment =
            requireActivity().supportFragmentManager.findFragmentByTag(SECOND_FRAGMENT_TAG)
                ?: SecondFragment.newInstance()

        val view = inflater.inflate(R.layout.fragment_first, container, false)
        view.findViewById<TextView>(R.id.firstFragmentHelloTextview).apply {
            text = text.toString() + ", param1 = $param1" + ", param2 = $param2"
            setOnClickListener {
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, secondFragment, SECOND_FRAGMENT_TAG)
                    .addToBackStack(null)
                    .commit()
            }
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: Int) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putInt(ARG_PARAM2, param2)
                }
            }
    }
}