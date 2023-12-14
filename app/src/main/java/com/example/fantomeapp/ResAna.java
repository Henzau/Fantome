package com.example.fantomeapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.function.ObjIntConsumer;
import com.example.fantomeapp.databinding.FragmentResAnaBinding;

public class ResAna extends Fragment {

    FragmentResAnaBinding binding;

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

        binding = FragmentResAnaBinding.inflate(inflater, container, false); //
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.androidVersion.setText("Android version : "+ analyse.currentVersion() +"\n");
        binding.kernelVersion.setText("Kernel version : "+analyse.readKernelVersion() +"\n");


    }
}