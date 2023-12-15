package com.example.fantomeapp;

import android.os.Build;
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


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.androidVersion.setText("Android version : "+ analyse.currentVersion() +"\n");
        binding.kernelVersion.setText("Kernel version : "+analyse.readKernelVersion() +"\n");

        if(Double.parseDouble(Build.VERSION.RELEASE.replaceAll("(\\d+[.]\\d+)(.*)","$1")) < 10) {
            binding.informationAndrobso.setText(getResources().getString(R.string.Hardening_to10));
        } else if ((Double.parseDouble(Build.VERSION.RELEASE.replaceAll("(\\d+[.]\\d+)(.*)","$1")) >= 10) && ((Double.parseDouble(Build.VERSION.RELEASE.replaceAll("(\\d+[.]\\d+)(.*)","$1")) < 14))) {
            binding.informationAndrobso.setText(getResources().getString(R.string.Hardening_from10to14));
        } else if (Double.parseDouble(Build.VERSION.RELEASE.replaceAll("(\\d+[.]\\d+)(.*)","$1")) >= 14){
            binding.informationAndrobso.setText(getResources().getString(R.string.Hardening_upto14));
        }

        boolean devmod_enable = analyse.isDevMode(this.getContext().getContentResolver());
        binding.button1.setText("dev mode is : "+ devmod_enable+"\n");
        binding.button2.setText("adb mode is : " + analyse.isADB(this.getContext().getContentResolver())+"\n"); //necessite des tests !

        binding.button3.setText("phone encrypted ? : "+analyse.isPhoneEncrypted()+"\n");
        binding.button4.setText("AVB state : "+analyse.isAVB()+"\n");
        binding.imageButtonRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The user just clicked
                if (getActivity() instanceof MainActivity) {
                    MainPage mainpage = MainPage.newInstance();
                    ((MainActivity) getActivity()).replaceFragment(mainpage);
                }
            }
        });

        // Affichage des pop-up
        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The user just clicked
                if (getActivity() instanceof MainActivity) {
                    MainPage mainpage = MainPage.newInstance();
                    ((MainActivity) getActivity()).showDialog(binding.button1,0);
                }
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The user just clicked
                if (getActivity() instanceof MainActivity) {
                    MainPage mainpage = MainPage.newInstance();
                    ((MainActivity) getActivity()).showDialog(binding.button2,1);
                }
            }
        });
        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The user just clicked
                if (getActivity() instanceof MainActivity) {
                    MainPage mainpage = MainPage.newInstance();
                    ((MainActivity) getActivity()).showDialog(binding.button3,2);
                }
            }
        });
        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The user just clicked
                if (getActivity() instanceof MainActivity) {
                    MainPage mainpage = MainPage.newInstance();
                    ((MainActivity) getActivity()).showDialog(binding.button4,3);
                }
            }
        });
        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The user just clicked
                if (getActivity() instanceof MainActivity) {
                    MainPage mainpage = MainPage.newInstance();
                    ((MainActivity) getActivity()).showDialog(binding.button5,4);
                }
            }
        });

    }

}