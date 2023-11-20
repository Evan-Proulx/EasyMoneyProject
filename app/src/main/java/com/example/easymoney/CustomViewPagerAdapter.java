
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
    private double totalExpenses;
    double savings = incomeValue-totalExpenses;
    private double housingPercent;
    private double insurancePercent;
    private double foodPercent;
    private double otherValue1Percent;

    public CustomViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, double incomeValue, double housingValue, double insuranceValue,
                                  double foodValue, double otherValue1, double totalExpenses, double savings, double housingPercent, double insurancePercent,
                                  double foodPercent, double otherValue1Percent) {

        super(fragmentActivity);

        this.incomeValue = incomeValue;
        this.housingValue = housingValue;
        this.insuranceValue = insuranceValue;
        this.foodValue = foodValue;
        this.otherValue1 = otherValue1;
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
        position = position%6;
        switch (position){
            case 0: return ResultFragment.newInstance("Income: \n" + "$"+incomeValue, "Total Spending: " + "$"+totalExpenses);
            case 1: return ResultFragment.newInstance("Housing: \n" + "$"+housingValue,  housingPercent + "%");
            case 2: return ResultFragment.newInstance("Insurance: \n" + "$"+insuranceValue, insurancePercent + "%");
            case 3: return ResultFragment.newInstance("Food: \n" + "$"+foodValue, foodPercent + "%");
            case 4: return ResultFragment.newInstance("Other: \n" + "$"+otherValue1, otherValue1Percent + "%");
            case 5: return ResultFragment.newInstance("Savings : \n" + savings, "$"+savings);
            default: return ResultFragment.newInstance("404: Fragment not found", "Try again :(");
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
