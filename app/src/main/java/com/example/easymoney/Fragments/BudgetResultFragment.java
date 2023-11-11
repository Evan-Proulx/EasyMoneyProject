package com.example.easymoney.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easymoney.R;
import com.example.easymoney.SharedViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BudgetResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BudgetResultFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SharedViewModel sharedViewModel;

    public BudgetResultFragment() {
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
    public static BudgetResultFragment newInstance(String param1, String param2) {
        BudgetResultFragment fragment = new BudgetResultFragment();
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
        Double incomeValue = receivedData.get(0);
        Double housingValue = receivedData.get(1);
        Double insuranceValue = receivedData.get(2);
        Double foodValue = receivedData.get(3);
        Double otherValue1 = receivedData.get(4);
        Double otherValue2 = receivedData.get(5);
        Double otherValue3 = receivedData.get(6);
        Double totalExpenses = receivedData.get(7);





        return view;
    }
    private ArrayList<Double> receiveDataFromFragment(){
        return sharedViewModel.getSharedData();
    }
}