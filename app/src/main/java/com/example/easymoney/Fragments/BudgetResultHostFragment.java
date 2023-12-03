package com.example.easymoney.Fragments;

import android.content.Intent;
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
import android.widget.Toast;

import com.example.easymoney.CurrencyUtil;
import com.example.easymoney.CustomViewPagerAdapter;
import com.example.easymoney.R;
import com.example.easymoney.SharedViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.lang.reflect.Array;
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
        View view = inflater.inflate(R.layout.fragment_budget_result, container, false);
        String selectedCurrency = CurrencyUtil.getSelectedCurrency(requireContext());
        String currencySymbol = CurrencyUtil.getCurrencySymbol(selectedCurrency);

        //Format data from the sharedViewModel
        ArrayList<Double> receivedData = receiveDataFromFragment();
        ArrayList<Double> formattedData = new ArrayList<>();
        for (double data : receivedData){
            String formattedValue = String.format("%.2f", data);
            formattedData.add(Double.parseDouble(formattedValue));
        }
        double incomeValue = formattedData.get(0);
        double housingValue = formattedData.get(1);
        double insuranceValue = formattedData.get(2);
        double foodValue = formattedData.get(3);
        double otherValue1 = formattedData.get(4);
        double totalExpenses = formattedData.get(5);
        double savings = incomeValue-totalExpenses;
        double savingsPercent = findPercent(savings, incomeValue);
        double housingPercent = findPercent(housingValue,incomeValue);
        double insurancePercent = findPercent(insuranceValue,incomeValue);
        double foodPercent = findPercent(foodValue,incomeValue);
        double otherValue1Percent = findPercent(otherValue1,incomeValue);

        viewPager2 = view.findViewById(R.id.viewpager);
        viewPager2.setAdapter(new CustomViewPagerAdapter(getActivity(), incomeValue, housingValue, insuranceValue, foodValue,
                otherValue1,totalExpenses, savings, savingsPercent, housingPercent, insurancePercent,
                foodPercent, otherValue1Percent));

        Button saveButton = view.findViewById(R.id.budgetSaveBtn);
        //This is all the data in the viewPagerAdapter formatted to fit in one string
        //This string is used in note intent
        String budgetToString = getString(R.string.income) + currencySymbol + incomeValue + "\n\n" +
                getString(R.string.total_spending) + currencySymbol +totalExpenses +
                "\n\n" + getString(R.string.housing) + currencySymbol +housingValue + " / " + housingPercent + getString(R.string.percent_symbol) +
                "\n\n" + getString(R.string.insurance) + currencySymbol +insuranceValue + " / " + insurancePercent + getString(R.string.percent_symbol) +
                "\n\n" + getString(R.string.food) + currencySymbol +foodValue + " / " + foodPercent + getString(R.string.percent_symbol) +
                "\n\n" + getString(R.string.other) + currencySymbol +otherValue1 + " / " + otherValue1Percent + getString(R.string.percent_symbol) +
                "\n\n" + getString(R.string.savings_result) + currencySymbol+savings + savingsPercent+getString(R.string.percent_symbol);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "Budget");
                i.putExtra(Intent.EXTRA_TEXT, budgetToString);

                Intent chooser = Intent.createChooser(i, "Choose a note app:");
                try {
                    startActivity(chooser);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getContext(),
                            "No Valid Application Installed",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        
        return view;
    }

    //create the tabLayout
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TabLayout tabLayout = view.findViewById(R.id.result_tab_layout);
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) ->
                tab.setText(("\u25CF"))).attach();
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