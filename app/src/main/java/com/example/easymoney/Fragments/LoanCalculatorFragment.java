package com.example.easymoney.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.easymoney.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoanCalculatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoanCalculatorFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoanCalculatorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoanCalculatorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoanCalculatorFragment newInstance(String param1, String param2) {
        LoanCalculatorFragment fragment = new LoanCalculatorFragment();
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
        View view = inflater.inflate(R.layout.fragment_loan_calculator, container, false);

        EditText loanAmount = view.findViewById(R.id.amountEditText);
        EditText termYears = view.findViewById(R.id.yearEditText);
        EditText termMonths = view.findViewById(R.id.monthEditText);
        TextView interestRatePercent = view.findViewById(R.id.interestRatePercentTextView);
        SeekBar interestRateSeek = view.findViewById(R.id.interestSeekBar);
        String selectedPaymentTime = "";
        String selectedCompound = "";


        RadioGroup paymentTimeRadioGroup = view.findViewById(R.id.paymentTimeRadioGroup);
        RadioGroup compoundRadioGroup = view.findViewById(R.id.compoundRadioGroup);
        int selectedPaymentId = paymentTimeRadioGroup.getCheckedRadioButtonId();
        int selectedCompoundId = compoundRadioGroup.getCheckedRadioButtonId();

        if (selectedPaymentId != -1) {
            RadioButton selectedPaymentButton = view.findViewById(selectedPaymentId);
            selectedPaymentTime = selectedPaymentButton.getText().toString();
        }

        if (selectedCompoundId != -1) {
            RadioButton selectedCompoundButton = view.findViewById(selectedCompoundId);
            selectedCompound = selectedCompoundButton.getText().toString();
        }

        interestRateSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                interestRatePercent.setText(String.valueOf(i) + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        String loanAmountText = loanAmount.getText().toString();
        String termYearsText = termYears.getText().toString();
        String termMonthsText = termMonths.getText().toString();

        //get number values
        double loanAmountValue = Double.parseDouble(loanAmountText);
        double interestRateValue = interestRateSeek.getProgress() / 100.0;
        int termYearsValue = Integer.parseInt(termYearsText);
        int termMonthsValue = Integer.parseInt(termMonthsText);
        int numOfPayments;
        double monthlyInterest;
        int compoundsPerYear;


        //Set interest based on when payments are due
        if (selectedPaymentTime.equals("Monthly")) {
            numOfPayments = termYearsValue * 12 + termMonthsValue;
            monthlyInterest = interestRateValue / 12;
        } else if (selectedPaymentTime.equals("Weekly")) {
            numOfPayments = termYearsValue * 52 + termMonthsValue * 4;
            monthlyInterest = interestRateValue / 52;
        } else {
            numOfPayments = termYearsValue;
            monthlyInterest = interestRateValue;
        }

        if (selectedCompound.equals("Monthly")) {
            compoundsPerYear = 12;
        } else if (selectedCompound.equals("Weekly")) {
            compoundsPerYear = 52;
        } else {
            compoundsPerYear = 1;
        }

        double calculatedInterestRate = interestRateValue / compoundsPerYear;
        System.out.println(calculatedInterestRate);
        double calculatedPayment = (monthlyInterest * loanAmountValue) / (1 - Math.pow(monthlyInterest, -numOfPayments));
        System.out.println(calculatedPayment);


        Button submitButton = view.findViewById(R.id.loanSubmitBtn);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    System.out.println("Your interest rate: " + calculatedInterestRate + "\n" +
                            "Your payment: " + calculatedPayment);
                }
        });

        return view;
    }


}
