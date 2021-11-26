package com.lavv.crossfitapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
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
    private String  movements_strigs[]={"Air Squat","Front Squat","Overhead Squat","Shoulder Press","Push Press","Push Jerk","Deadlift"};

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

        /*
        Here we make the validation of a not later date than the actual one and after that we
        send to the Database
        */

        binding.Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((nYear <= ACTUAL_YEAR && nMonth <= ACTUAL_MONTH && nDay <= ACTUAL_DAY)||(nYear <= ACTUAL_YEAR && nMonth < ACTUAL_MONTH)||(nYear < ACTUAL_YEAR)) {
                    //Toast.makeText(getApplicationContext(), "Nice", Toast.LENGTH_SHORT).show();
                    String date_s=String.valueOf(nYear)+"-"+String.valueOf(nMonth+1)+"-"+String.valueOf(nDay)+" "+String.valueOf(nHour)+":"+String.valueOf(nMinute)+":00";
                    String movements="";
                    for( int i=0;i<checkExercises.length;i++){
                        Log.i("data",String.valueOf(checkExercises[i]));
                        if(checkExercises[i]==true){
                            movements=movements+movements_strigs[i]+",";
                        }
                    }
                    Session sessionModel;
                    try{
                        sessionModel= new Session(-1, date_s, comments,movements);

                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(),"Error creating session",Toast.LENGTH_SHORT).show();
                        sessionModel= new Session(-1, "YYYY-MM-DD HH:MM:SS", "comments dummy","No movements");
                    }

                    DataBaseHelper dataBaseHelper= new DataBaseHelper(getApplicationContext());
                    boolean response= dataBaseHelper.addSession(sessionModel);
                    Toast.makeText(getApplicationContext(),"Success="+response,Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getApplicationContext(), "Set a Valid Date", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*
        This button helps the user to return to the Main Menu
         */
        binding.Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
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


    /*
    This sets the booleans in the checkExercises Boolean Array to true of false if the checkbox
    that matches with the specified exercise is checked or not
    */
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

    /*
    with this onSaveInstanceState
     */

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
