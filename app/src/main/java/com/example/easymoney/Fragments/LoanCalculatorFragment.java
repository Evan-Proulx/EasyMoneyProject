package com.example.easymoney.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

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
import com.example.easymoney.SharedViewModel;

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
    private SharedViewModel sharedViewModel;

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
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
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

        RadioGroup paymentTimeRadioGroup = view.findViewById(R.id.paymentTimeRadioGroup);
        RadioGroup compoundRadioGroup = view.findViewById(R.id.compoundRadioGroup);
        RadioButton paymentWeekly = view.findViewById(R.id.paymentRadioWeekly);
        RadioButton paymentMonthly = view.findViewById(R.id.paymentRadioMonthly);
        RadioButton paymentAnnual = view.findViewById(R.id.paymentRadioAnnual);
        RadioButton compoundWeekly = view.findViewById(R.id.compoundRadioWeekly);
        RadioButton compoundMonthly = view.findViewById(R.id.compoundRadioMonthly);
        RadioButton compoundAnnual = view.findViewById(R.id.compoundRadioAnnual);

//        int paymentWeeklyId= paymentWeekly.getId();
//        int paymentMonthlyId= paymentMonthly.getId();
//        int paymentAnnualId = paymentAnnual.getId();
//        int compoundWeeklyId = compoundWeekly.getId();
//        int compoundMonthlyId = compoundMonthly.getId();
//        int compoundAnnualId = compoundAnnual.getId();

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



        //get number values


        Button submitButton = view.findViewById(R.id.loanSubmitBtn);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numOfPayments;
                double monthlyInterest;
                int compoundsPerYear = 2;

                double loanAmountValue = Double.parseDouble(loanAmount.getText().toString());
                double interestRateValue = interestRateSeek.getProgress() / 100.0;
                int termYearsValue = Integer.parseInt(termYears.getText().toString());
                int termMonthsValue = Integer.parseInt(termMonths.getText().toString());

                System.out.println("Term months " + termMonthsValue+"\n" +
                        "Term years " + termYearsValue + "\n" +
                        "interest rate " + interestRateValue);


                //Set interest based on when payments are due
                if (paymentMonthly.isChecked()) {
                    numOfPayments = termYearsValue * 12 + termMonthsValue;
                    monthlyInterest = interestRateValue / 12;
                    System.out.println("monthly checked");

                } else if (paymentWeekly.isChecked()) {
                    numOfPayments = termYearsValue * 52 + termMonthsValue * 4;
                    monthlyInterest = interestRateValue / 52;
                    System.out.println("weekly checked");
                } else {
                    numOfPayments = termYearsValue;
                    monthlyInterest = interestRateValue;
                    System.out.println("eyarlye checked");
                }

                if (compoundMonthly.isChecked()) {
                    compoundsPerYear = 12;
                    System.out.println("monthly checked");
                } else if (compoundWeekly.isChecked()) {
                    compoundsPerYear = 52;
                    System.out.println("weekly checked");

                } else {
                    compoundsPerYear = 1;
                    System.out.println("eyarlye checked");
                }

                double calculatedInterestRate = interestRateValue / compoundsPerYear;
                System.out.println(calculatedInterestRate);
                double calculatedPayment = (monthlyInterest * loanAmountValue) / (1 - Math.pow(1+monthlyInterest, -numOfPayments));
                System.out.println(calculatedPayment);

                    System.out.println("Your interest rate: " + calculatedInterestRate + "\n" +
                            "Your payment: " + calculatedPayment);

                Navigation.findNavController(view)
                        .navigate(R.id.action_nav_loan_to_loanResultFragment);

            }
        });

        return view;
    }


}
