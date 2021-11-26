package com.lavv.crossfitapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.lavv.crossfitapp.DB.Db;
import com.lavv.crossfitapp.DB.appDbClient;
import com.lavv.crossfitapp.databinding.ActivityBinnacleshowBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class BinnacleShowActivity extends AppCompatActivity {
    private ActivityBinnacleshowBinding binding;
    private ArrayList<Session> sessions = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBinnacleshowBinding.inflate(getLayoutInflater());
        setupFullScreen();
        setContentView(binding.getRoot());
        setSessions();
        sortSessions(sessions);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            addToLayout();
        }
        //binding.entriesLayout.addView();

        binding.Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SessionDetailsActivity.class);
                startActivity(intent);
            }
        });

        binding.goToMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        binding.toAllEntries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DataShowActivity.class);
                startActivity(intent);
            }
        });

        binding.entriesLayout.getChildAt(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "aiura", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void sortSessions(ArrayList<Session> session){
        session.sort(Comparator.comparing(o -> o.getDate()));
    }

    private void setSessions(){
        //String Result = "";
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
            //Result += file.getFecha()+",";
            //Result += file.getHora()+",";
            //Result += file.getAct1()+",";
            //Result += file.getAct2()+",";
            //Result += file.getAct3()+",";
            //Result += file.getAct4()+",";
            //Result += file.getAct5()+",";
            //Result += file.getAct6()+",";
            //Result += file.getAct7()+",";
            //Result += file.getComment()+",";
        }
        //Log.i("assa", Result);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SimpleDateFormat")
    private void addToLayout(){
        for(int i = sessions.size() - 1; i >= 0; i--){
            String result = "";
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy\nhh:mm");
            String date = formatter.format(sessions.get(i).getDate());
            result += date;
            //result += sessions.get(i).getMovements();

            //result += sessions.get(i).getComment();
            TextView textView = new TextView(this);
            textView.setText(result);
            textView.setTextSize(24);
            textView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            Random rand = new Random();
            textView.setBackgroundColor(Color.rgb(rand.nextInt(155) + 100, rand.nextInt(155) + 100, rand.nextInt(155) + 100));
            textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            textView.setAutoSizeTextTypeUniformWithConfiguration(10, 32, 1, TypedValue.COMPLEX_UNIT_SP);
            int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SimpleDateFormat formatter_Date = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat formatter_Hour = new SimpleDateFormat("HH:mm");
                    Intent intent = new Intent(getApplicationContext(), SessionDetailsActivity.class);
                    intent.putExtra("movements", sessions.get(finalI).getMovements());
                    intent.putExtra("date", formatter_Date.format(sessions.get(finalI).getDate()));
                    intent.putExtra("time", formatter_Hour.format(sessions.get(finalI).getDate()));
                    intent.putExtra("comments", sessions.get(finalI).getComment());
                    startActivity(intent);
                }
            });
            binding.entriesLayout.addView(textView);

        }
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
