package com.example.fantomeapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fantomeapp.databinding.FragmentMainPageBinding;


public class MainPage extends Fragment {
    private FragmentMainPageBinding binding;


    public MainPage() {
        // Required empty public constructor
    }

    public static MainPage newInstance(String param1, String param2) {
        MainPage fragment = new MainPage();
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
        binding = FragmentMainPageBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        binding.buttonLancer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // The user just clicked
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ResAna resarena = ResAna.newInstance();
                fragmentTransaction.add(R.id.fragment_container_view, resarena);
                fragmentTransaction.commit();
            }
        });

        binding.imageButtonInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // The user just clicked
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                InfoFrag infoFragment = InfoFrag.newInstance();
                fragmentTransaction.add(R.id.fragment_container_view, infoFragment);
                fragmentTransaction.commit();
            }
        });


    }
}