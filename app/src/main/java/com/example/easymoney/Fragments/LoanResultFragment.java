package com.example.easymoney.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.easymoney.R;
import com.example.easymoney.SharedViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoanResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoanResultFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SharedViewModel sharedViewModel;


    public LoanResultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoanResultFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoanResultFragment newInstance(String param1, String param2) {
        LoanResultFragment fragment = new LoanResultFragment();
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
        View view = inflater.inflate(R.layout.fragment_loan_result, container, false);

        TextView paymentTitleTextView = view.findViewById(R.id.paymentTitleTextView);
        TextView paymentTextView = view.findViewById(R.id.paymentTextView);
        TextView totalPaymentTitleTextView = view.findViewById(R.id.totalPaymentTitleTextView);
        TextView totalPaymentTextView = view.findViewById(R.id.totalPaymentTextView);
        TextView totalInterestTitleTextView = view.findViewById(R.id.totalInterestTitleTextView);
        TextView totalInterestTextView = view.findViewById(R.id.totalInterestTextView);


        ArrayList<Double> receivedData = receiveDataFromFragment();
        double calculatedInterestRate = receivedData.get(0);
        double calculatedPayment = receivedData.get(1);
        double totalPayment = receivedData.get(2);
        double totalInterest = receivedData.get(3);
        double paymentTime = receivedData.get(4);

        //checks what payment time was selected and changes text
        switch ((int) paymentTime){
            case 1: paymentTitleTextView.setText("Monthly Payments");break;
            case 2: paymentTitleTextView.setText("Weekly  Payments");break;
            case 3: paymentTitleTextView.setText("Yearly Payments");break;
        }

        paymentTextView.setText(getString(R.string.dollar_format, String.valueOf(calculatedPayment)));
        totalPaymentTextView.setText(getString(R.string.dollar_format, String.valueOf(totalPayment)));
        totalInterestTextView.setText(getString(R.string.dollar_format, String.valueOf(totalInterest)));


        LinearLayout resultLinearLayout = view.findViewById(R.id.resultLinearLayout);
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.loan_animation);
        resultLinearLayout.startAnimation(animation);
        return view;
    }

    //gets data sent from the LoanCalculatorFragment
    private ArrayList<Double> receiveDataFromFragment(){
        return sharedViewModel.getSharedData();
    }

}