package com.example.easymoney.Fragments;

import android.app.LauncherActivity;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easymoney.CustomRecyclerViewAdapter;
import com.example.easymoney.ListItem;
import com.example.easymoney.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoFragment newInstance(String param1, String param2) {
        InfoFragment fragment = new InfoFragment();
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
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        ArrayList<ListItem> listItems = new ArrayList<>();
        listItems.add(new ListItem("Create a Budget","Make a detailed budget that outlines your monthly income and expenses. Categorize your spending to identify areas where you can cut back."));
        listItems.add(new ListItem("Emergency Fund","Make a detailed budget that outlines your monthly income and expenses. Categorize your spending to identify areas where you can cut back."));
        listItems.add(new ListItem("Track Your Spending","Keep track of every expense, no matter how small. This will help you identify areas where you can cut back and ensure you stay within your budget.\n"));
        listItems.add(new ListItem("Prioritize Saving","Treat saving as a non-negotiable expense. Set aside a portion of your income each month for savings before spending on other discretionary items.\n"));
        listItems.add(new ListItem("Automate Savings","Set up automatic transfers to your savings account. This makes saving a habit and ensures that you consistently contribute to your savings goals.\n"));
        listItems.add(new ListItem("Cut Unnecessary Expenses","Identify and eliminate unnecessary expenses. Evaluate your subscriptions, dining out, and impulse purchases. Small savings can add up over time.\n"));
        listItems.add(new ListItem("Compare Prices","Before making a purchase, compare prices online or at different stores. Look for discounts, use coupons, and take advantage of sales to save money.\n"));

        RecyclerView recyclerView = view.findViewById(R.id.recycle);
        CustomRecyclerViewAdapter adapter = new CustomRecyclerViewAdapter(listItems);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }
}