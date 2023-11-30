package com.example.easymoney.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.CheckBoxPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreferenceCompat;

import com.example.easymoney.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        CheckBoxPreference disableAnimationsPreference = findPreference("anim_disable");
        if (disableAnimationsPreference != null) {
            disableAnimationsPreference.setOnPreferenceChangeListener((preference, newValue) -> {
                boolean disableAnimations = (boolean) newValue;

                // Handle the preference change, e.g., save it to SharedPreferences
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
                preferences.edit().putBoolean("anim_disable", disableAnimations).apply();

                // Return true to update the preference's state
                return true;
            });
        }
    }
}

