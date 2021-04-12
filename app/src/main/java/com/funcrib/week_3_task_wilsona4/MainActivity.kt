package com.funcrib.week_3_task_wilsona4

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*Instantiate Shared Preference to persist values on app exit and re-entry*/
        val appSettingsPreferences: SharedPreferences = getSharedPreferences("AppSettingsPreferences", 0)
        val sharedPreferencesEdit: SharedPreferences.Editor = appSettingsPreferences.edit()

        /*Get the current state of night mode*/
        val isNightModeOn: Boolean = appSettingsPreferences.getBoolean("Night Mode", false)

        /*On App Launch, Check Night Mode State saved from SharedPreference and Set Switch and Greeting text accordingly*/

        if (isNightModeOn) {
            nightModeSwitch.isChecked = true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            tvAppbarTitle.text = getString(R.string.good_night)
        } else {
            nightModeSwitch.isChecked = false
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            tvAppbarTitle.text = getString(R.string.good_morning)
        }

        /*Set night mode onClick of switch*/
        nightModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            /*Set the Night mode then save as shared preference*/
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedPreferencesEdit.putBoolean("Night Mode", true)
                sharedPreferencesEdit.apply()
                /*Set the Greeting Text accordingly*/
                tvAppbarTitle.text = getString(R.string.good_night)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedPreferencesEdit.putBoolean("Night Mode", false)
                sharedPreferencesEdit.apply()
                /*Set the Greeting Text accordingly*/
                tvAppbarTitle.text = getString(R.string.good_morning)
            }
        }
    }
}