package com.example.fantomeapp;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;
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

        boolean value_button1 = analyse.isDevMode(this.getContext().getContentResolver());
        boolean value_button2 = analyse.isADB(this.getContext().getContentResolver());
        String res_button3 = analyse.isPhoneEncrypted(); // a changer en boolean
        boolean value_button3 = Objects.equals(res_button3, "encrypted");
        String res_button4 = analyse.isAVB(); // a changer en boolean
        boolean value_button4 = Objects.equals(res_button4,"enforcing");
        String res_button5 = analyse.isolation_final(analyse.isolation1(),analyse.isolation2(),analyse.isolation3());
        String targetString = "Isolation conforme";
        boolean value_button5 = res_button5.startsWith(targetString);
        String[] Keysecu = analyse.isInsideSecureHardware();
        String res_button6 = Keysecu[0];
        boolean value_button6 = Objects.equals(res_button6,"StrongBox présente");
        String res_button7 = Keysecu[1];
        boolean value_button7 = Objects.equals(res_button7,"Sécurité hardware");
        String value_button8 = analyse.LastSecurityPatch();

        binding.button1.setText("dev mode is : "+ value_button1+"\n");

        binding.button2.setText("adb mode is : " + value_button2+"\n"); //necessite des tests !

        binding.button3.setText("phone encrypted ? : "+value_button3+"\n");
        binding.button4.setText("AVB state : "+res_button4+"\n");

        binding.button5.setText(res_button5);
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

        binding.button6.setText(res_button6);
        binding.button7.setText(res_button7);
        binding.button8.setText("Last Security Patch : " + value_button8+"\n");


        //Affichage des boutons de la bonne couleur.

        if(value_button1){
            binding.button1.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.not_good));
        }
        if(value_button2){
            binding.button2.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.not_good));
        }
        if(!value_button3){
            binding.button3.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.not_good));
        }
        if(!value_button4){
            binding.button4.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.not_good));
        }
        if(!value_button5){
            binding.button5.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.not_good));
        }
        if(!value_button6){
            binding.button6.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.not_good));
        }
        if(!value_button7){
            binding.button6.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.not_good));
        }



        // Affichage des pop-up
        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The user just clicked
                if (getActivity() instanceof MainActivity) {
                    MainPage mainpage = MainPage.newInstance();
                    ((MainActivity) getActivity()).showDialog(binding.button1, 0);
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
        binding.button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The user just clicked
                if (getActivity() instanceof MainActivity) {
                    MainPage mainpage = MainPage.newInstance();
                    ((MainActivity) getActivity()).showDialog(binding.button6,5);
                }
            }
        });
        binding.button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The user just clicked
                if (getActivity() instanceof MainActivity) {
                    MainPage mainpage = MainPage.newInstance();
                    ((MainActivity) getActivity()).showDialog(binding.button7,6);
                }
            }
        });

        binding.button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The user just clicked
                if (getActivity() instanceof MainActivity) {
                    MainPage mainpage = MainPage.newInstance();
                    ((MainActivity) getActivity()).showDialog(binding.button8,7);
                }
            }
        });



    }

}