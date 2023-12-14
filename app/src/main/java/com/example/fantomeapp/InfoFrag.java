package com.example.fantomeapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fantomeapp.databinding.FragmentInfoBinding;
import com.example.fantomeapp.databinding.FragmentMainPageBinding;

public class InfoFrag extends Fragment {
    FragmentInfoBinding binding;
    public InfoFrag() {
        // Required empty public constructor
    }
    public static InfoFrag newInstance() {
        InfoFrag fragment = new InfoFrag();
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
        binding = FragmentInfoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.imageButtonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The user just clicked
                if (getActivity() instanceof MainActivity) {
                    MainPage mainpage = MainPage.newInstance();
                    ((MainActivity) getActivity()).replaceFragment(mainpage);
                }

            }
        });
    }

}