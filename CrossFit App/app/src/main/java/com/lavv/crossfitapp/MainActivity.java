/**
 * This is the main activity class of the application.
 * This class takes care of the interaction with the user when first entering the application.
 * This class helps the user to navigate and explore the other functionalities.
 */
package com.lavv.crossfitapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.lavv.crossfitapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setupFullScreen();
        setContentView(binding.getRoot());

        //Setting the Button listener, When this button is pressed it takes us to DataShowActivity
        binding.statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DataShowActivity.class);
                startActivity(intent);
            }
        });

        //Setting the Button listener, When this button is pressed it takes us to the ExerciseSelector
        binding.movementGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ExerciseSelector.class);
                startActivity(intent);
            }
        });

        //Setting the Button listener, When this button is pressed it takes us to the BinnacleActivity
        binding.binnacle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BinnacleActivity.class);
                startActivity(intent);
            }
        });

        //Setting the Button listener, When this button is pressed it takes us to the BinnacleShowActivity
        binding.userWorkouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BinnacleShowActivity.class);
                startActivity(intent);
            }
        });

    }

    //Full screen setup
    private void setupFullScreen() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupFullScreen();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setupFullScreen();
    }
}