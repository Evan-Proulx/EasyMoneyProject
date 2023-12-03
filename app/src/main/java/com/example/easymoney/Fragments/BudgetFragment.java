package com.example.easymoney.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.easymoney.R;
import com.example.easymoney.SharedViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BudgetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BudgetFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    FragmentManager fm;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SharedViewModel sharedViewModel;

    public BudgetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BudgetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BudgetFragment newInstance(String param1, String param2) {
        BudgetFragment fragment = new BudgetFragment();
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
        View view = inflater.inflate(R.layout.fragment_budget, container, false);

        TextView titleText = view.findViewById(R.id.titleText);
        TextView incomeTextView = view.findViewById(R.id.incomeTextView);
        TextView housingTextView = view.findViewById(R.id.housingTextView);
        TextView insuranceTextView = view.findViewById(R.id.insuranceTextView);
        TextView foodTextView = view.findViewById(R.id.foodTextView);
        TextView other1TextView = view.findViewById(R.id.other1TextView);

        EditText incomeEditText = view.findViewById(R.id.editTextIncome);
        EditText housingEditText = view.findViewById(R.id.housingEditText);
        EditText insuranceEditText = view.findViewById(R.id.insuranceEditText);
        EditText foodEditText = view.findViewById(R.id.foodEditText);
        EditText other1EditText = view.findViewById(R.id.other1EditText);
        Button resetBtn = view.findViewById(R.id.resetBtn);
        Button submitBtn = view.findViewById(R.id.saveButton);


        //reset editText text
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incomeEditText.setText("");
                housingEditText.setText("");
                insuranceEditText.setText("");
                foodEditText.setText("");
                other1EditText.setText("");
            }
        });

        //calls sendDataToFragment and sets teh values arrayList as an argument
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double incomeValue = Double.parseDouble(incomeEditText.getText().toString());
                double housingValue = Double.parseDouble(housingEditText.getText().toString());
                double insuranceValue = Double.parseDouble(insuranceEditText.getText().toString());
                double foodValue = Double.parseDouble(foodEditText.getText().toString());
                double other1Value = Double.parseDouble(other1EditText.getText().toString());
                double totalExpenses = housingValue + insuranceValue + foodValue + other1Value;

                //Add edit text values to arraylist
                ArrayList<Double> values = new ArrayList<>();
                values.add(incomeValue);
                values.add(housingValue);
                values.add(insuranceValue);
                values.add(foodValue);
                values.add(other1Value);
                values.add(totalExpenses);

                sendDataToFragment(values);
                Navigation.findNavController(view)
                        .navigate(R.id.action_nav_budget_to_budgetResultFragment);
            }
        });

        return view;
    }

    //Takes arraylist values and sends it to another fragment
    private void sendDataToFragment(ArrayList<Double> values){
        sharedViewModel.setSharedData(values);
    }
}