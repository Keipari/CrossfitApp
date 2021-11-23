package com.lavv.crossfitapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.lavv.crossfitapp.databinding.ActivityBinnacleBinding;
import java.util.Calendar;

public class BinnacleActivity extends AppCompatActivity {

    private ActivityBinnacleBinding binding;
    final Calendar calendar = Calendar.getInstance();
    private final int ACTUAL_HOUR = calendar.get(Calendar.HOUR_OF_DAY), ACTUAL_MINUTE = calendar.get(Calendar.MINUTE);
    private final int ACTUAL_DAY = calendar.get(Calendar.DAY_OF_MONTH), ACTUAL_MONTH = calendar.get(Calendar.MONTH), ACTUAL_YEAR = calendar.get(Calendar.YEAR);
    private int nHour, nMinute, nDay, nMonth, nYear;
    private String comments;
    private boolean[] checkExercises;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBinnacleBinding.inflate(getLayoutInflater());
        setupFullScreen();
        setContentView(binding.getRoot());
        checkExercises = new boolean[]{false, false, false, false, false, false, false};
        binding.Time.setText(ACTUAL_HOUR + ":" + ACTUAL_MINUTE);
        binding.actualD.setText(ACTUAL_DAY + "/" + (ACTUAL_MONTH + 1) + "/" + ACTUAL_YEAR);
        initializeBaseValues(savedInstanceState);

        binding.Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nYear == ACTUAL_YEAR){
                    if(nMonth == ACTUAL_MONTH){
                        if(nDay <= ACTUAL_DAY){
                            Toast.makeText(getApplicationContext(), "Nice", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Set a Valid Date", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        if (nMonth < ACTUAL_MONTH){
                            Toast.makeText(getApplicationContext(), "Nice", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Set a Valid Date", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    if(nYear < ACTUAL_YEAR){
                        Toast.makeText(getApplicationContext(), "Nice", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Set a Valid Date", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.AirSquatCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBooleanChecked(view.getId());
            }
        });
        binding.FrontSquatCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBooleanChecked(view.getId());
            }
        });
        binding.OverheadSquatCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBooleanChecked(view.getId());
            }
        });
        binding.ShoulderPressCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBooleanChecked(view.getId());
            }
        });
        binding.PushPressCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBooleanChecked(view.getId());
            }
        });
        binding.PushJerkCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBooleanChecked(view.getId());
            }
        });
        binding.DeadliftCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBooleanChecked(view.getId());
            }
        });

        binding.comments.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                comments = String.valueOf(editable);
                Log.i("comments", comments);
            }
        });

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
                                nHour = sHour;
                                nMinute = sMinute;

                                setupFullScreen();
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

                                binding.actualD.setText(day + "/" + (month + 1) + "/" + year);
                                setupFullScreen();

                                nDay = day;
                                nMonth = month;
                                nYear = year;
                                Log.i("Date", nDay + "/" + (nMonth + 1) + "/" + nYear);

                            }
                        }, year, month, day);
                datePicker.setTitle("Select a Date");
                datePicker.show();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void initializeBaseValues(Bundle savedInstanceState){
        if(savedInstanceState != null){
            checkExercises = savedInstanceState.getBooleanArray("checkboxes");
            nHour = savedInstanceState.getInt("hour", 0);
            nMinute = savedInstanceState.getInt("minute", 0);
            nDay = savedInstanceState.getInt("day", Calendar.DAY_OF_MONTH);
            nMonth = savedInstanceState.getInt("month", Calendar.MONTH);
            nYear = savedInstanceState.getInt("year", Calendar.YEAR);
            comments = savedInstanceState.getString("comments", String.valueOf(R.string.empty));

            for(int i = 0; i < 7; i++){
                Log.i("what happens", String.valueOf(checkExercises[i]));
            }


            binding.AirSquatCheck.setChecked(checkExercises[0]);
            binding.FrontSquatCheck.setChecked(checkExercises[1]);
            binding.OverheadSquatCheck.setChecked(checkExercises[2]);
            binding.ShoulderPressCheck.setChecked(checkExercises[3]);
            binding.PushPressCheck.setChecked(checkExercises[4]);
            binding.PushJerkCheck.setChecked(checkExercises[5]);
            binding.DeadliftCheck.setChecked(checkExercises[6]);
            binding.comments.setText(comments);
            binding.Time.setText(nHour + ":" + nMinute);
            binding.actualD.setText(nDay + "/" + nMonth + "/" + nYear);

        }else{
            checkExercises = new boolean[]{false, false, false, false, false, false, false};
        }
    }

    private void setupFullScreen() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @SuppressLint("NonConstantResourceId")
    private void setBooleanChecked(int id){
        switch(id){
            case R.id.AirSquatCheck:
                checkExercises[0] = binding.AirSquatCheck.isChecked();
                Log.i("FrontSquat", String.valueOf(checkExercises[0]));
                break;
            case R.id.FrontSquatCheck:
                checkExercises[1] = binding.FrontSquatCheck.isChecked();
                Log.i("FrontSquat", String.valueOf(checkExercises[1]));
                break;
            case R.id.OverheadSquatCheck:
                checkExercises[2] = binding.OverheadSquatCheck.isChecked();
                Log.i("OverheadSquat", String.valueOf(checkExercises[2]));
                break;
            case R.id.ShoulderPressCheck:
                checkExercises[3] = binding.ShoulderPressCheck.isChecked();
                Log.i("ShoulderPress", String.valueOf(checkExercises[3]));
                break;
            case R.id.PushPressCheck:
                checkExercises[4] = binding.PushPressCheck.isChecked();
                Log.i("PushPress", String.valueOf(checkExercises[4]));
                break;
            case R.id.PushJerkCheck:
                checkExercises[5] = binding.PushJerkCheck.isChecked();
                Log.i("PushJerk", String.valueOf(checkExercises[5]));
                break;
            case R.id.DeadliftCheck:
                checkExercises[6] = binding.DeadliftCheck.isChecked();
                Log.i("Deadlift", String.valueOf(checkExercises[6]));
                break;
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("hour", nHour);
        outState.putInt("minute", nMinute);
        outState.putInt("day", nDay);
        outState.putInt("month", nMonth);
        outState.putInt("year", nYear);
        outState.putBooleanArray("checkboxes", checkExercises);
        outState.putString("comments", comments);
        super.onSaveInstanceState(outState);
    }
}
