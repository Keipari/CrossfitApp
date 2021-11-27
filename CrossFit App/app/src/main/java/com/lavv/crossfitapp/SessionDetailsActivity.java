/**
 * This Activity class of the interaction with the user when viewing the details of a specific session.
 * Here the display of all the information of a session is handled.
 */
package com.lavv.crossfitapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lavv.crossfitapp.databinding.ActivityDetailsBinding;
import com.lavv.crossfitapp.dblogic.Session;


public class SessionDetailsActivity extends AppCompatActivity {
    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setupFullScreen();
        setContentView(binding.getRoot());
        //Getting the Session object
        Session session = (Session) getIntent().getSerializableExtra("session");

        //Displaying Movements
        String[] movements_array = session.getMovements().split(",");
        String cadena="";
        for (int i =0; i<movements_array.length;i++){
            cadena=cadena+movements_array[i]+"\n";
        }
        binding.setExercises.setText(cadena);
        Log.i("M", cadena);
        // Displaying Date
        if (session.getDate_session().contains(" ")){
            String[] datetime = session.getDate_session().split(" ");
            binding.textdate.setText(datetime[0]);
            binding.texttime.setText(datetime[1]);
            Log.i("fecha", datetime[0]+datetime[1]);
        }

        //Displaying Coments
        binding.setComments.setText(session.getComment());
        Log.i("comentario", session.getComment());

        //Setting the Button listener, When this button is pressed it takes us to the main activity
        binding.goToMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        //Setting the Button listener, When this button is pressed it takes us to the BinnacleShowActivity
        binding.toAllEntries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BinnacleShowActivity.class);
                startActivity(intent);
            }
        });
    }

    //Setting the full screen
    private void setupFullScreen() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

}
