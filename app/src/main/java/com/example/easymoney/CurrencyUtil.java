package com.example.easymoney;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.example.easymoney.Fragments.SettingsFragment;

public class CurrencyUtil {

    public static String getSelectedCurrency(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("currency_preference", "USD");
    }

   public static String getCurrencySymbol(String currencyCode) {
        switch (currencyCode.toLowerCase()) {
            case "usd":
                return "$";
            case "eur":
                return "€";
            case "gbp":
                return "£";
            // Add more cases for other currencies
            default:
                return "$"; // Default to "$" if the currency code is unknown
        }
    }




}
