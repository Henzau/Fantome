package com.example.fantomeapp;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fantomeapp.MainActivity;
import com.example.fantomeapp.R;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 10000; // Durée de l'animation de démarrage en millisecondes

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Récupérer l'ImageView à partir de la mise en page
        ImageView imageView = findViewById(R.id.imageView);

        // Définir l'animation drawable
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();

        // Démarrer l'animation
        animationDrawable.start();

        // Utiliser un Handler pour retarder le passage à l'activité principale
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
            finish();
        }, SPLASH_DURATION);
    }
}
