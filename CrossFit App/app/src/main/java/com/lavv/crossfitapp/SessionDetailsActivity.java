package com.lavv.crossfitapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lavv.crossfitapp.databinding.ActivityDetailsBinding;

import java.io.Serializable;

public class SessionDetailsActivity extends AppCompatActivity {
    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setupFullScreen();
        setContentView(binding.getRoot());
        Session session = (Session) getIntent().getSerializableExtra("session");
        //Movimientos
        String[] movements_array = session.getMovements().split(",");
        String cadena="";
        for (int i =0; i<movements_array.length;i++){
            cadena=cadena+movements_array[i]+"\n";
        }
        binding.setExercises.setText(cadena);
        Log.i("M", cadena);
        //Date
        String[] datetime = session.getDate_session().split(" ");
        binding.date.setText(datetime[0]);
        binding.time.setText(datetime[1]);
        Log.i("fecha", datetime[0]+datetime[1]);
        //Coments
        binding.setComments.setText(session.getComment());
        Log.i("comentario", session.getComment());

        binding.goToMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
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
