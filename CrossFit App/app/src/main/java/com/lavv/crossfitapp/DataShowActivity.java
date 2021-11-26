package com.lavv.crossfitapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.lavv.crossfitapp.DB.Db;
import com.lavv.crossfitapp.DB.appDbClient;
import com.lavv.crossfitapp.databinding.ActivityDatashowBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class DataShowActivity extends AppCompatActivity {
    private ActivityDatashowBinding binding;
    private ArrayList<Session> sessions = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDatashowBinding.inflate(getLayoutInflater());
        setupFullScreen();
        setSessions();
        sortSessions(sessions);
        setContentView(binding.getRoot());

        binding.GetEntries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.entriesLayout.removeAllViewsInLayout();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    addToLayout(binding.SpinnerMonth.getSelectedItem().toString(), binding.spinnerExercise.getSelectedItem().toString());
                }
            }
        });

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


        /*
        Here we correct the behaviour of the spinner item selector in front of screen by calling again the setupFullScreen() method
         */

        binding.spinnerExercise.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setupFullScreen();
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                setupFullScreen();
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void sortSessions(ArrayList<Session> session){
        session.sort(Comparator.comparing(o -> o.getDate()));
    }

    private void setSessions(){
        List<Db> result = appDbClient.getInstance(getApplicationContext()).getAppDatabase().test().getAll();
        for (Db file : result){
            boolean[] moves = new boolean[]{
                    file.getAct1(),
                    file.getAct2(),
                    file.getAct3(),
                    file.getAct4(),
                    file.getAct5(),
                    file.getAct6(),
                    file.getAct7()
            };
            Session session = new Session(file.getFecha(), file.getHora(), file.getComment(), moves);
            sessions.add(session);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SimpleDateFormat")
    private void addToLayout(String month, String exercise){
        for(int i = sessions.size() - 1; i >= 0; i--){
            switch(month){
                case "January":
                    if(sessions.get(i).getOnlyDate().contains("/01/") || sessions.get(i).getOnlyDate().contains("/1/")){
                        switch(exercise){
                            case "Air Squat":
                                if(sessions.get(i).getMovements().contains("Air Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Front Squat":
                                if(sessions.get(i).getMovements().contains("Front Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Overhead Squat":
                                if(sessions.get(i).getMovements().contains("Overhead Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Shoulder Press":
                                if(sessions.get(i).getMovements().contains("Shoulder Press")){
                                    correctSession(i);
                                }
                                break;
                            case "Push Press":
                                if(sessions.get(i).getMovements().contains("Push Press")){
                                    correctSession(i);
                                }
                                break;
                            case "Push Jerk":
                                if(sessions.get(i).getMovements().contains("Push Jerk")){
                                    correctSession(i);
                                }
                                break;
                            case "Deadlift":
                                if(sessions.get(i).getMovements().contains("Deadlift")){
                                    correctSession(i);
                                }
                                break;

                        }
                    }
                    break;
                case "February":
                    if(sessions.get(i).getOnlyDate().contains("/02/") || sessions.get(i).getOnlyDate().contains("/2/")){
                        switch(exercise){
                            case "Air Squat":
                                if(sessions.get(i).getMovements().contains("Air Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Front Squat":
                                if(sessions.get(i).getMovements().contains("Front Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Overhead Squat":
                                if(sessions.get(i).getMovements().contains("Overhead Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Shoulder Press":
                                if(sessions.get(i).getMovements().contains("Shoulder Press")){
                                    correctSession(i);
                                }
                                break;
                            case "Push Press":
                                if(sessions.get(i).getMovements().contains("Push Press")){
                                    correctSession(i);
                                }
                                break;
                            case "Push Jerk":
                                if(sessions.get(i).getMovements().contains("Push Jerk")){
                                    correctSession(i);
                                }
                                break;
                            case "Deadlift":
                                if(sessions.get(i).getMovements().contains("Deadlift")){
                                    correctSession(i);
                                }
                                break;

                        }
                    }
                    break;
                case "March":
                    if(sessions.get(i).getOnlyDate().contains("/03/") || sessions.get(i).getOnlyDate().contains("/3/")){
                        switch(exercise){
                            case "Air Squat":
                                if(sessions.get(i).getMovements().contains("Air Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Front Squat":
                                if(sessions.get(i).getMovements().contains("Front Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Overhead Squat":
                                if(sessions.get(i).getMovements().contains("Overhead Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Shoulder Press":
                                if(sessions.get(i).getMovements().contains("Shoulder Press")){
                                    correctSession(i);
                                }
                                break;
                            case "Push Press":
                                if(sessions.get(i).getMovements().contains("Push Press")){
                                    correctSession(i);
                                }
                                break;
                            case "Push Jerk":
                                if(sessions.get(i).getMovements().contains("Push Jerk")){
                                    correctSession(i);
                                }
                                break;
                            case "Deadlift":
                                if(sessions.get(i).getMovements().contains("Deadlift")){
                                    correctSession(i);
                                }
                                break;

                        }
                    }
                    break;
                case "April":
                    if(sessions.get(i).getOnlyDate().contains("/04/") || sessions.get(i).getOnlyDate().contains("/4/")){
                        switch(exercise){
                            case "Air Squat":
                                if(sessions.get(i).getMovements().contains("Air Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Front Squat":
                                if(sessions.get(i).getMovements().contains("Front Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Overhead Squat":
                                if(sessions.get(i).getMovements().contains("Overhead Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Shoulder Press":
                                if(sessions.get(i).getMovements().contains("Shoulder Press")){
                                    correctSession(i);
                                }
                                break;
                            case "Push Press":
                                if(sessions.get(i).getMovements().contains("Push Press")){
                                    correctSession(i);
                                }
                                break;
                            case "Push Jerk":
                                if(sessions.get(i).getMovements().contains("Push Jerk")){
                                    correctSession(i);
                                }
                                break;
                            case "Deadlift":
                                if(sessions.get(i).getMovements().contains("Deadlift")){
                                    correctSession(i);
                                }
                                break;

                        }
                    }
                    break;
                case "May":
                    if(sessions.get(i).getOnlyDate().contains("/05/") || sessions.get(i).getOnlyDate().contains("/5/")){
                        switch(exercise){
                            case "Air Squat":
                                if(sessions.get(i).getMovements().contains("Air Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Front Squat":
                                if(sessions.get(i).getMovements().contains("Front Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Overhead Squat":
                                if(sessions.get(i).getMovements().contains("Overhead Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Shoulder Press":
                                if(sessions.get(i).getMovements().contains("Shoulder Press")){
                                    correctSession(i);
                                }
                                break;
                            case "Push Press":
                                if(sessions.get(i).getMovements().contains("Push Press")){
                                    correctSession(i);
                                }
                                break;
                            case "Push Jerk":
                                if(sessions.get(i).getMovements().contains("Push Jerk")){
                                    correctSession(i);
                                }
                                break;
                            case "Deadlift":
                                if(sessions.get(i).getMovements().contains("Deadlift")){
                                    correctSession(i);
                                }
                                break;

                        }
                    }
                    break;
                case "June":
                    if(sessions.get(i).getOnlyDate().contains("/06/") || sessions.get(i).getOnlyDate().contains("/6/")){
                        switch(exercise){
                            case "Air Squat":
                                if(sessions.get(i).getMovements().contains("Air Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Front Squat":
                                if(sessions.get(i).getMovements().contains("Front Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Overhead Squat":
                                if(sessions.get(i).getMovements().contains("Overhead Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Shoulder Press":
                                if(sessions.get(i).getMovements().contains("Shoulder Press")){
                                    correctSession(i);
                                }
                                break;
                            case "Push Press":
                                if(sessions.get(i).getMovements().contains("Push Press")){
                                    correctSession(i);
                                }
                                break;
                            case "Push Jerk":
                                if(sessions.get(i).getMovements().contains("Push Jerk")){
                                    correctSession(i);
                                }
                                break;
                            case "Deadlift":
                                if(sessions.get(i).getMovements().contains("Deadlift")){
                                    correctSession(i);
                                }
                                break;

                        }
                    }
                    break;
                case "July":
                    if(sessions.get(i).getOnlyDate().contains("/07/") || sessions.get(i).getOnlyDate().contains("/7/")){
                        switch(exercise){
                            case "Air Squat":
                                if(sessions.get(i).getMovements().contains("Air Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Front Squat":
                                if(sessions.get(i).getMovements().contains("Front Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Overhead Squat":
                                if(sessions.get(i).getMovements().contains("Overhead Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Shoulder Press":
                                if(sessions.get(i).getMovements().contains("Shoulder Press")){
                                    correctSession(i);
                                }
                                break;
                            case "Push Press":
                                if(sessions.get(i).getMovements().contains("Push Press")){
                                    correctSession(i);
                                }
                                break;
                            case "Push Jerk":
                                if(sessions.get(i).getMovements().contains("Push Jerk")){
                                    correctSession(i);
                                }
                                break;
                            case "Deadlift":
                                if(sessions.get(i).getMovements().contains("Deadlift")){
                                    correctSession(i);
                                }
                                break;

                        }
                    }
                    break;
                case "August":
                    if(sessions.get(i).getOnlyDate().contains("/08/") || sessions.get(i).getOnlyDate().contains("/8/")){
                        switch(exercise){
                            case "Air Squat":
                                if(sessions.get(i).getMovements().contains("Air Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Front Squat":
                                if(sessions.get(i).getMovements().contains("Front Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Overhead Squat":
                                if(sessions.get(i).getMovements().contains("Overhead Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Shoulder Press":
                                if(sessions.get(i).getMovements().contains("Shoulder Press")){
                                    correctSession(i);
                                }
                                break;
                            case "Push Press":
                                if(sessions.get(i).getMovements().contains("Push Press")){
                                    correctSession(i);
                                }
                                break;
                            case "Push Jerk":
                                if(sessions.get(i).getMovements().contains("Push Jerk")){
                                    correctSession(i);
                                }
                                break;
                            case "Deadlift":
                                if(sessions.get(i).getMovements().contains("Deadlift")){
                                    correctSession(i);
                                }
                                break;

                        }
                    }
                    break;
                case "September":
                    if(sessions.get(i).getOnlyDate().contains("/09/") || sessions.get(i).getOnlyDate().contains("/9/")){
                        switch(exercise){
                            case "Air Squat":
                                if(sessions.get(i).getMovements().contains("Air Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Front Squat":
                                if(sessions.get(i).getMovements().contains("Front Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Overhead Squat":
                                if(sessions.get(i).getMovements().contains("Overhead Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Shoulder Press":
                                if(sessions.get(i).getMovements().contains("Shoulder Press")){
                                    correctSession(i);
                                }
                                break;
                            case "Push Press":
                                if(sessions.get(i).getMovements().contains("Push Press")){
                                    correctSession(i);
                                }
                                break;
                            case "Push Jerk":
                                if(sessions.get(i).getMovements().contains("Push Jerk")){
                                    correctSession(i);
                                }
                                break;
                            case "Deadlift":
                                if(sessions.get(i).getMovements().contains("Deadlift")){
                                    correctSession(i);
                                }
                                break;

                        }
                    }
                    break;
                case "October":
                    if(sessions.get(i).getOnlyDate().contains("/10/")){
                        switch(exercise){
                            case "Air Squat":
                                if(sessions.get(i).getMovements().contains("Air Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Front Squat":
                                if(sessions.get(i).getMovements().contains("Front Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Overhead Squat":
                                if(sessions.get(i).getMovements().contains("Overhead Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Shoulder Press":
                                if(sessions.get(i).getMovements().contains("Shoulder Press")){
                                    correctSession(i);
                                }
                                break;
                            case "Push Press":
                                if(sessions.get(i).getMovements().contains("Push Press")){
                                    correctSession(i);
                                }
                                break;
                            case "Push Jerk":
                                if(sessions.get(i).getMovements().contains("Push Jerk")){
                                    correctSession(i);
                                }
                                break;
                            case "Deadlift":
                                if(sessions.get(i).getMovements().contains("Deadlift")){
                                    correctSession(i);
                                }
                                break;

                        }
                    }
                    break;
                case "November":
                    if(sessions.get(i).getOnlyDate().contains("/11/")){
                        switch(exercise){
                            case "Air Squat":
                                if(sessions.get(i).getMovements().contains("Air Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Front Squat":
                                if(sessions.get(i).getMovements().contains("Front Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Overhead Squat":
                                if(sessions.get(i).getMovements().contains("Overhead Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Shoulder Press":
                                if(sessions.get(i).getMovements().contains("Shoulder Press")){
                                    correctSession(i);
                                }
                                break;
                            case "Push Press":
                                if(sessions.get(i).getMovements().contains("Push Press")){
                                    correctSession(i);
                                }
                                break;
                            case "Push Jerk":
                                if(sessions.get(i).getMovements().contains("Push Jerk")){
                                    correctSession(i);
                                }
                                break;
                            case "Deadlift":
                                if(sessions.get(i).getMovements().contains("Deadlift")){
                                    correctSession(i);
                                }
                                break;

                        }
                    }
                    break;
                case "December":
                    if(sessions.get(i).getOnlyDate().contains("/12/")){
                        switch(exercise){
                            case "Air Squat":
                                if(sessions.get(i).getMovements().contains("Air Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Front Squat":
                                if(sessions.get(i).getMovements().contains("Front Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Overhead Squat":
                                if(sessions.get(i).getMovements().contains("Overhead Squat")){
                                    correctSession(i);
                                }
                                break;
                            case "Shoulder Press":
                                if(sessions.get(i).getMovements().contains("Shoulder Press")){
                                    correctSession(i);
                                }
                                break;
                            case "Push Press":
                                if(sessions.get(i).getMovements().contains("Push Press")){
                                    correctSession(i);
                                }
                                break;
                            case "Push Jerk":
                                if(sessions.get(i).getMovements().contains("Push Jerk")){
                                    correctSession(i);
                                }
                                break;
                            case "Deadlift":
                                if(sessions.get(i).getMovements().contains("Deadlift")){
                                    correctSession(i);
                                }
                                break;

                        }
                    }
                    break;

            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void correctSession(int i){
        String result = "";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy\nhh:mm");
        String date = formatter.format(sessions.get(i).getDate());
        result += date + "\n";
        result += sessions.get(i).getMovements();
        result += sessions.get(i).getComment();
        TextView textView = new TextView(this);
        textView.setText(result);
        textView.setTextSize(24);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Random rand = new Random();
        textView.setBackgroundColor(Color.rgb(rand.nextInt(155) + 100, rand.nextInt(155) + 100, rand.nextInt(155) + 100));
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView.setAutoSizeTextTypeUniformWithConfiguration(10, 32, 1, TypedValue.COMPLEX_UNIT_SP);
        binding.entriesLayout.addView(textView);
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
