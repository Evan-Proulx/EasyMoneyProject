package com.example.easymoney.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easymoney.ListItem;
import com.example.easymoney.R;
import com.example.easymoney.adapters.CustomRecyclerViewAdapter;

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
        listItems.add(new ListItem(getString(R.string.create_budget_title), getString(R.string.create_budget_description)));
        listItems.add(new ListItem(getString(R.string.prepare_emergency_title), getString(R.string.prepare_emergency_description)));
        listItems.add(new ListItem(getString(R.string.track_spending_title), getString(R.string.track_spending_description)));
        listItems.add(new ListItem(getString(R.string.prioritize_saving_title), getString(R.string.prioritize_saving_description)));
        listItems.add(new ListItem(getString(R.string.cook_at_home_title), getString(R.string.cook_at_home_description)));
        listItems.add(new ListItem(getString(R.string.cut_expenses_title), getString(R.string.cut_expenses_description)));
        listItems.add(new ListItem(getString(R.string.compare_prices_title), getString(R.string.compare_prices_description)));

        RecyclerView recyclerView = view.findViewById(R.id.recycle);
        CustomRecyclerViewAdapter adapter = new CustomRecyclerViewAdapter(listItems);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}