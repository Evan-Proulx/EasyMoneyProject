package com.example.easymoney;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class SharedViewModel extends ViewModel {
    private ArrayList<Double> sharedData;

    public ArrayList<Double> getSharedData(){
        return sharedData;
    }

    public void setSharedData(ArrayList<Double> sharedData) {
        this.sharedData = sharedData;
    }
}
