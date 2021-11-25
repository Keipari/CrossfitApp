package com.lavv.crossfitapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.lavv.crossfitapp.databinding.ActivityDatashowBinding;

public class DataShowActivity extends AppCompatActivity {
    private ActivityDatashowBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDatashowBinding.inflate(getLayoutInflater());
        setupFullScreen();
        setContentView(binding.getRoot());


        //With this we return to the Main Menu Activity
        binding.goToMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        //With this we can access to the activity with all the entries of the DB
        binding.toAllEntries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BinnacleShowActivity.class);
                startActivity(intent);
            }
        });


        //Con est se añaden los nuevos datos en forma de TextView/Button/X al LinearLayout del ScrollView
        //binding.entriesLayout.addView(prueba);

        //Con este método obtienes el valor en string del objeto: binding.spinnerExercise.getSelectedItem().toString());
        //Con este método obtienes la posicion en el arreglo de string del item: binding.spinnerExercise.getSelectedItemId()

        //Here we correct the behaviour of the spinner item selector in front of screen by calling again the setupFullScreen() method
        binding.spinnerExercise.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setupFullScreen();
                Log.i("spinner Item", binding.spinnerExercise.getSelectedItem().toString());
                Log.i("spinner Item", binding.spinnerExercise.getSelectedItemId() + "");
                //((TextView) (adapterView.getChildAt(0))).setAutoSizeTextTypeUniformWithConfiguration(10, 48, 1, TypedValue.COMPLEX_UNIT_DIP);
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                setupFullScreen();
            }
        });

        //Here we correct the behaviour of the spinner item selection in front of screen by calling again the setupFullScreen() method
        binding.SpinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setupFullScreen();
                //((TextView) (adapterView.getChildAt(0))).setAutoSizeTextTypeUniformWithConfiguration(10, 48, 1, TypedValue.COMPLEX_UNIT_DIP);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                setupFullScreen();
            }
        });


    }


    //This method is used to set the app in fullscreen with no action or title bar from AndroidStudio
    private void setupFullScreen() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

}
