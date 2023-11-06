package com.example.easymoney.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.easymoney.R;

import org.w3c.dom.Text;

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

        int housingValue = 0;
        int insuranceValue = 0;
        int foodValue = 0;
        int other1Value = 0;
        int other2Value = 0;
        int other3Value = 0;

        int incomeValue = 0;
        int totalExpenses = 0;

        try{
            housingValue = Integer.parseInt(housingEditText.getText().toString());
            insuranceValue = Integer.parseInt(insuranceEditText.getText().toString());
            foodValue = Integer.parseInt(foodEditText.getText().toString());
            other1Value = Integer.parseInt(other1EditText.getText().toString());
            other2Value = Integer.parseInt(other2EditText.getText().toString());
            other3Value = Integer.parseInt(other3EditText.getText().toString());

            incomeValue = Integer.parseInt(incomeEditText.getText().toString());
            totalExpenses = housingValue + insuranceValue + foodValue + other1Value + other2Value + other3Value;
        }catch (NumberFormatException e){
            e.printStackTrace();
        }

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

            fm = getActivity().getSupportFragmentManager();

            Bundle bundle = new Bundle();
            bundle.putInt("1", housingValue);
            bundle.putInt("2", insuranceValue);
            bundle.putInt("3", foodValue);
            bundle.putInt("4", other1Value);
            bundle.putInt("5", other2Value);
            bundle.putInt("6", other3Value);
            bundle.putInt("7", incomeValue);
            bundle.putInt("8", totalExpenses);


            submitBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  fm.beginTransaction().replace(R.id.info_fragment_container, new BudgetResultFragment()).addToBackStack(null).commit();
                }
            });

//        FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_container, budgetResultFragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();


        return view;
    }
}