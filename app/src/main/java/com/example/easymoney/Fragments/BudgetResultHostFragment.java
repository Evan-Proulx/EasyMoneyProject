package com.example.easymoney.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.easymoney.CustomViewPagerAdapter;
import com.example.easymoney.R;
import com.example.easymoney.SharedViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BudgetResultHostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BudgetResultHostFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SharedViewModel sharedViewModel;

    ViewPager2 viewPager2;



    public BudgetResultHostFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BudgetResultFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BudgetResultHostFragment newInstance(String param1, String param2) {
        BudgetResultHostFragment fragment = new BudgetResultHostFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_budget_result, container, false);

        ArrayList<Double> receivedData = receiveDataFromFragment();
        double incomeValue = receivedData.get(0);
        double housingValue = receivedData.get(1);
        double insuranceValue = receivedData.get(2);
        double foodValue = receivedData.get(3);
        double otherValue1 = receivedData.get(4);
        double otherValue2 = receivedData.get(5);
        double otherValue3 = receivedData.get(6);
        double totalExpenses = receivedData.get(7);
        double savings = incomeValue-totalExpenses;
        double housingPercent = findPercent(housingValue,incomeValue);
        double insurancePercent = findPercent(insuranceValue,incomeValue);
        double foodPercent = findPercent(foodValue,incomeValue);
        double otherValue1Percent = findPercent(otherValue1,incomeValue);
        double otherValue2Percent = findPercent(otherValue2,incomeValue);
        double otherValue3Percent = findPercent(otherValue3,incomeValue);


        viewPager2 = view.findViewById(R.id.viewpager);
        viewPager2.setAdapter(new CustomViewPagerAdapter(getActivity(), incomeValue, housingValue, insuranceValue, foodValue,
                otherValue1, otherValue2, otherValue3, totalExpenses,
                savings, housingPercent, insurancePercent,
                foodPercent, otherValue1Percent));
        
        return view;
    }
    //gets data sent from the budgetFragment
    private ArrayList<Double> receiveDataFromFragment(){
        return sharedViewModel.getSharedData();
    }

    //find percent of two numbers
    public static double findPercent(double first, double second){
        if (second != 0){
            return (first/second) * 100;
        }else return 0;
    }
}