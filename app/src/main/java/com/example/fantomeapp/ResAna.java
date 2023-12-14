package com.example.fantomeapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResAna#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResAna extends Fragment {


    public ResAna() {
        // Required empty public constructor
    }

    public static ResAna newInstance() {
        ResAna fragment = new ResAna();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_res_ana, container, false);
    }


}