package com.example.easymoney;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.easymoney.Fragments.ResultFragment;

public class CustomViewPagerAdapter extends FragmentStateAdapter {

    private double incomeValue;
    private double housingValue;
    private double insuranceValue;
    private double foodValue;
    private double otherValue1;
    private double otherValue2;
    private double otherValue3;
    private double totalExpenses;
    double savings = incomeValue-totalExpenses;
    private double housingPercent;
    private double insurancePercent;
    private double foodPercent;
    private double otherValue1Percent;

    public CustomViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, double incomeValue, double housingValue, double insuranceValue,
                                  double foodValue, double otherValue1, double otherValue2, double otherValue3,
                                  double totalExpenses, double savings, double housingPercent, double insurancePercent, double foodPercent, double otherValue1Percent) {

        super(fragmentActivity);

        this.incomeValue = incomeValue;
        this.housingValue = housingValue;
        this.insuranceValue = insuranceValue;
        this.foodValue = foodValue;
        this.otherValue1 = otherValue1;
        this.otherValue2 = otherValue2;
        this.otherValue3 = otherValue3;
        this.totalExpenses = totalExpenses;
        this.savings = savings;
        this.housingPercent = housingPercent;
        this.insurancePercent = insurancePercent;
        this.foodPercent = foodPercent;
        this.otherValue1Percent = otherValue1Percent;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return ResultFragment.newInstance("Income: " + incomeValue, "Total: " + totalExpenses);
            case 1: return ResultFragment.newInstance("Housing: " + housingValue, "%" + housingPercent);
            case 2: return ResultFragment.newInstance("Insurance: " + insuranceValue, "%" + insurancePercent);
            case 3: return ResultFragment.newInstance("Food: " + foodValue, "%" + foodPercent);
            case 4: return ResultFragment.newInstance("Other: " + otherValue1, "%" + otherValue1Percent);
            default: return ResultFragment.newInstance("404: Fragment not found", "Try again :(");
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
