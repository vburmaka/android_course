package com.example.lesson09preference

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import java.util.Date

private const val COUNTER_KEY = "COUNTER_KEY"
private const val LAST_MODIFIED_KEY = "LAST_MODIFIED_KEY"

class SettingsActivity : AppCompatActivity() {

    private var counterTextView: TextView? = null
    private var lastModifiedTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
//        setContentView(R.layout.shared_pref_demo_activity)
//        counterTextView = findViewById(R.id.counterTextView)
//        lastModifiedTextView = findViewById(R.id.lastModifiedTextView)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onResume() {
        super.onResume()
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        counterTextView?.text = "App was open ${sharedPref.getInt(COUNTER_KEY, -1)} times"
        lastModifiedTextView?.text = "App was modified on ${sharedPref.getString(LAST_MODIFIED_KEY, "No data")}"
    }

    override fun onPause() {
        super.onPause()
        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        var counter = sharedPref.getInt(COUNTER_KEY, 0)
        with (sharedPref.edit()) {
            putInt(COUNTER_KEY, ++counter)
            putString(LAST_MODIFIED_KEY, Date().toString())
            apply()
        }
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
    }
}