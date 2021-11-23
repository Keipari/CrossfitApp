package com.lavv.crossfitapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lavv.crossfitapp.databinding.ActivityBinnacleBinding;

import java.util.Calendar;

public class BinnacleActivity extends AppCompatActivity {

    private ActivityBinnacleBinding binding;
    private int hour, minute;
    private int aDay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBinnacleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupFullScreen();

        binding.getTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);

                //time picker dialog
                TimePickerDialog timePicker = new TimePickerDialog(BinnacleActivity.this, android.R.style.Theme_Holo_Light_Dialog,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker timePicker, int sHour, int sMinute) {
                                if(sHour == 0){
                                    if(sMinute == 0){
                                        binding.Time.setText("00:00");
                                    }else{
                                        binding.Time.setText("00:" + sMinute);
                                    }
                                }else{
                                    if(sMinute == 0){
                                        binding.Time.setText(sHour + ":00");
                                    }else{
                                        binding.Time.setText(sHour + ":" + sMinute);
                                    }
                                }
                            }
                        }, hour, minutes, true);
                timePicker.setTitle("Select a Time");
                timePicker.show();
            }
        });

        binding.getDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                //date picker dialog

                DatePickerDialog datePicker = new DatePickerDialog(BinnacleActivity.this, android.R.style.Theme_Holo_Light_Dialog,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                                binding.actualD.setText(day + "/" + month + "/" + year);

                            }
                        }, year, month, day);
                datePicker.setTitle("Select a Date");
                datePicker.show();
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
