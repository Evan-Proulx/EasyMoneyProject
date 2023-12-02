package com.example.easymoney.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.CheckBoxPreference;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
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

                // Change the state of disable animation setting
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
                preferences.edit().putBoolean("anim_disable", disableAnimations).apply();

                return true;
            });
        }

        EditTextPreference userNamePreference = (EditTextPreference) findPreference("userName");
        userNamePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                // The user name has changed
                String newName = (String) newValue;
                // You can save it to SharedPreferences or perform any necessary actions
                return true; // Returning true means the change is accepted
            }
        });
    }
}

