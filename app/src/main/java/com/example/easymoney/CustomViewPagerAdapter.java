
package com.example.easymoney;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.easymoney.Fragments.ResultFragment;
import com.example.easymoney.Fragments.SettingsFragment;

public class CustomViewPagerAdapter extends FragmentStateAdapter {

    private double incomeValue;
    private double housingValue;
    private double insuranceValue;
    private double foodValue;
    private double otherValue1;
    private double totalExpenses;
    double savings = incomeValue-totalExpenses;
    private double savingsPercent;
    private double housingPercent;
    private double insurancePercent;
    private double foodPercent;
    private double otherValue1Percent;

    private Context context;

    public CustomViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, double incomeValue, double housingValue, double insuranceValue,
                                  double foodValue, double otherValue1, double totalExpenses, double savings, double savingsPercent, double housingPercent, double insurancePercent,
                                  double foodPercent, double otherValue1Percent) {

        super(fragmentActivity);

        this.incomeValue = incomeValue;
        this.housingValue = housingValue;
        this.insuranceValue = insuranceValue;
        this.foodValue = foodValue;
        this.otherValue1 = otherValue1;
        this.totalExpenses = totalExpenses;
        this.savings = savings;
        this.savingsPercent = savingsPercent;
        this.housingPercent = housingPercent;
        this.insurancePercent = insurancePercent;
        this.foodPercent = foodPercent;
        this.otherValue1Percent = otherValue1Percent;
        this.context = fragmentActivity;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        String selectedCurrency = CurrencyUtil.getSelectedCurrency(context);
        String currencySymbol = CurrencyUtil.getCurrencySymbol(selectedCurrency);

        //Display data from budget fragment in viewPager
        position = position %6;
        switch (position){
            case 0: return ResultFragment.newInstance(context.getString(R.string.income) + currencySymbol +incomeValue, context.getString(R.string.total_spending) + currencySymbol +totalExpenses);
            case 1: return ResultFragment.newInstance(context.getString(R.string.housing) + currencySymbol +housingValue,  housingPercent + context.getString(R.string.percent_symbol));
            case 2: return ResultFragment.newInstance(context.getString(R.string.insurance) + currencySymbol +insuranceValue, insurancePercent + context.getString(R.string.percent_symbol));
            case 3: return ResultFragment.newInstance(context.getString(R.string.food) + currencySymbol +foodValue, foodPercent + context.getString(R.string.percent_symbol));
            case 4: return ResultFragment.newInstance(context.getString(R.string.other) + currencySymbol +otherValue1, otherValue1Percent + context.getString(R.string.percent_symbol));
            case 5: return ResultFragment.newInstance(context.getString(R.string.savings_result)+ currencySymbol + savings, savingsPercent + context.getString(R.string.percent_symbol));
            default: return ResultFragment.newInstance(context.getString(R.string.error), context.getString(R.string.error_try_again));
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
