package com.example.goodday

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

class SettingFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

    }

    private val modeChangeListener =
        Preference.OnPreferenceChangeListener { preference, newValue ->
            Log.i("newValue", newValue.toString())
            newValue as? String
            when (newValue) {
                getString(R.string.pref_night_on) -> {
                    updateTheme(AppCompatDelegate.MODE_NIGHT_YES)
                }
                getString(R.string.pref_night_off) -> {
                    updateTheme(AppCompatDelegate.MODE_NIGHT_NO)
                }
                else -> {
//                    if (BuildCompat.isAtLeastQ()) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
//                    } else {
//                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
//                    }
                }
            }
            true
        }

    private fun updateTheme(nightMode: Int): Boolean {
        AppCompatDelegate.setDefaultNightMode(nightMode)
        requireActivity().recreate()
        return true
    }


}