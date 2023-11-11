package com.example.easymoney.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.easymoney.R;
import com.example.easymoney.SharedViewModel;

import org.w3c.dom.Text;

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
        TextView other2TextView = view.findViewById(R.id.other2TextView);
        TextView other3TextView = view.findViewById(R.id.other3TextView);

        EditText incomeEditText = view.findViewById(R.id.editTextIncome);
        EditText housingEditText = view.findViewById(R.id.housingEditText);
        EditText insuranceEditText = view.findViewById(R.id.insuranceEditText);
        EditText foodEditText = view.findViewById(R.id.foodEditText);
        EditText other1EditText = view.findViewById(R.id.other1EditText);
        EditText other2EditText = view.findViewById(R.id.other2EditText);
        EditText other3EditText = view.findViewById(R.id.other3EditText);
        Button resetBtn = view.findViewById(R.id.resetBtn);
        Button submitBtn = view.findViewById(R.id.submitBtn);

        //Set values in edit text to Double
        Double housingValue = Double.parseDouble(housingEditText.getText().toString());
        Double insuranceValue = Double.parseDouble(insuranceEditText.getText().toString());
        Double foodValue = Double.parseDouble(foodEditText.getText().toString());
        Double other1Value = Double.parseDouble(other1EditText.getText().toString());
        Double other2Value = Double.parseDouble(other2EditText.getText().toString());
        Double other3Value =Double.parseDouble(other3EditText.getText().toString());

        Double incomeValue = Double.parseDouble(incomeEditText.getText().toString());
        Double totalExpenses = housingValue + insuranceValue + foodValue + other1Value + other2Value + other3Value;


        //Add edit text values to arraylist
        ArrayList<Double> values = new ArrayList<>();
        values.add(incomeValue);
        values.add(housingValue);
        values.add(insuranceValue);
        values.add(foodValue);
        values.add(other1Value);
        values.add(other2Value);
        values.add(other3Value);
        values.add(totalExpenses);



        //reset editText text
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incomeEditText.setText("");
                housingEditText.setText("");
                insuranceEditText.setText("");
                foodEditText.setText("");
                other1EditText.setText("");
                other2EditText.setText("");
                other3EditText.setText("");
            }
        });

        //calls sendDataToFragment and sets teh values arrayList as an argument
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendDataToFragment(values);
            }
        });

        return view;
    }

    //Takes arraylist values and sends it to another fragment
    private void sendDataToFragment(ArrayList<Double> values){
        sharedViewModel.setSharedData(values);
    }
}