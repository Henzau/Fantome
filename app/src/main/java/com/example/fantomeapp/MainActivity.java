package com.example.fantomeapp;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import com.example.fantomeapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_view, new MainPage())
                    .commit();
        }
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Check if there are fragments in the back stack
                if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                    // Pop the fragment from the back stack
                    getSupportFragmentManager().popBackStack();
                } else {
                    // No fragments in the back stack, handle the back press as needed
                    finish();
                }
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }
    void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_view, fragment)
                .addToBackStack(null)  // Ajoute la transaction à la pile de retour
                .commit();
    }

     void showDialog(Button button,int i){
        Dialog dialog = new Dialog(this, R.style.DialogStyle);
        dialog.setContentView(R.layout.layout_custom_dialog);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.bg_window);
        TextView Titre = dialog.findViewById(R.id.txttitle);
        TextView Description = dialog.findViewById(R.id.txtDesc);
        ImageView btnClose = dialog.findViewById(R.id.btn_close);
        Titre.setText(SecurityTips.getTitle(i));
        Description.setText(SecurityTips.getDesc(i));

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}