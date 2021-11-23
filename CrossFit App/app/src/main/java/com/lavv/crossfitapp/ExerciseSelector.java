package com.lavv.crossfitapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.lavv.crossfitapp.databinding.ActivityMovementBinding;

public class ExerciseSelector extends AppCompatActivity {

    private ActivityMovementBinding binding;
    static final String TYPE_OF_EXERCISE = "Type of Exercise";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovementBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupFullScreen();
        binding.AirSquat.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ExerciseViewer.class);
            intent.putExtra(TYPE_OF_EXERCISE, 0);
            startActivity(intent);
        });

        binding.FrontSquat.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ExerciseViewer.class);
            intent.putExtra(TYPE_OF_EXERCISE, 1);
            startActivity(intent);
        });

        binding.OverheadSquat.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ExerciseViewer.class);
            intent.putExtra(TYPE_OF_EXERCISE, 2);
            startActivity(intent);
        });

        binding.ShoulderPress.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ExerciseViewer.class);
            intent.putExtra(TYPE_OF_EXERCISE, 3);
            startActivity(intent);
        });

        binding.PushPress.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ExerciseViewer.class);
            intent.putExtra(TYPE_OF_EXERCISE, 4);
            startActivity(intent);
        });

        binding.PushJerk.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ExerciseViewer.class);
            intent.putExtra(TYPE_OF_EXERCISE, 5);
            startActivity(intent);
        });

        binding.Deadlift.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ExerciseViewer.class);
            intent.putExtra(TYPE_OF_EXERCISE, 6);
            startActivity(intent);
        });

    }

    private void setupFullScreen() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }



}
