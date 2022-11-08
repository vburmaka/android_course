package com.example.lesson05fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs

class SecondFragment : Fragment() {
    val args: SecondFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        val titleTextView: TextView = view.findViewById(R.id.secondFragmentTitleTextView)
        titleTextView.text = "Args: first = ${args.firstArg}, second = ${args.secondArg}"
        return view
    }

    interface SecondFragmentInteractionListener {

    }
}