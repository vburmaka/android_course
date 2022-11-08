package com.example.lesson05fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment

class NavigationDemoActivity : AppCompatActivity(), FirstFragment.FirstFragmentInteractionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_demo)
    }

    override fun navigateToFeature2(name: String, id: Int) {
        val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(firstArg = name, secondArg = id)
        findNavController(R.id.nav_host_fragment_demo).navigate(action)
    }
}