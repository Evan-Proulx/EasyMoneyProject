package com.example.easymoney.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.easymoney.CurrencyUtil;
import com.example.easymoney.ListItem;
import com.example.easymoney.R;
import com.example.easymoney.SharedViewModel;
import com.example.easymoney.adapters.CustomListViewAdapter;

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


        String paymentTitle = getString(R.string.loan_default_payment_title);
        String totalPaymentTitle = getString(R.string.loan_total_payment_title);
        String totalInterestTitle = getString(R.string.loan_total_interest_title);
        String totalPayment;
        String totalInterest;

        String selectedCurrency = CurrencyUtil.getSelectedCurrency(getContext());
        String currencySymbol = CurrencyUtil.getCurrencySymbol(selectedCurrency);

        ArrayList<Double> receivedData = receiveDataFromFragment();
        String calculatedInterestRate = currencySymbol + receivedData.get(0).toString();
        String calculatedPayment = currencySymbol + receivedData.get(1).toString();
        totalPayment = currencySymbol + receivedData.get(2).toString();
        totalInterest = currencySymbol + receivedData.get(3).toString();
        double paymentTime = receivedData.get(4);

        //checks what payment time was selected and changes text
        switch ((int) paymentTime){
            case 1: paymentTitle = getString(R.string.loan_monthly_payment_title);break;
            case 2: paymentTitle = getString(R.string.loan_weekly_payment_title);break;
            case 3: paymentTitle = getString(R.string.loan_yearly_payment_title);break;
        }

        ArrayList<ListItem> listItems = new ArrayList<>();
        listItems.add(new ListItem(paymentTitle, calculatedPayment));
        listItems.add(new ListItem(totalPaymentTitle, totalPayment));
        listItems.add(new ListItem(totalInterestTitle, totalInterest));

        CustomListViewAdapter adapter = new CustomListViewAdapter(getContext(), listItems);

        ListView listView = view.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        LinearLayout resultLinearLayout = view.findViewById(R.id.resultLinearLayout);

        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.loan_animation);
        //checks to see if animations are disabled in preferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean animationDisabled = preferences.getBoolean("anim_disable", false);
        if (!animationDisabled) {resultLinearLayout.startAnimation(animation);}

            return view;
    }

    //gets data sent from the LoanCalculatorFragment
    private ArrayList<Double> receiveDataFromFragment(){
        return sharedViewModel.getSharedData();
    }

}