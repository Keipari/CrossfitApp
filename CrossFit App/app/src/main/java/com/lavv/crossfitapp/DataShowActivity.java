package com.lavv.crossfitapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.lavv.crossfitapp.databinding.ActivityDatashowBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DataShowActivity extends AppCompatActivity {
    private ActivityDatashowBinding binding;

    private String month=" ",movement=" ";

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

        //Here we correct the behaviour of the spinner item selector in front of screen by calling again the setupFullScreen() method
        binding.spinnerExercise.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setupFullScreen();
                movement= binding.spinnerExercise.getSelectedItem().toString();
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
                month= String.valueOf(binding.SpinnerMonth.getSelectedItemId()+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                setupFullScreen();
            }
        });

        binding.GetEntries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (month!=" " && movement!=" "){

                    DataBaseHelper dataBaseHelper= new DataBaseHelper(getApplicationContext());
                    List<Session> sessions = dataBaseHelper.getSessionsByMonthMovements(month,movement);
                    int count = dataBaseHelper.getStatisticsCount(month,movement);
                    binding.count.setText("Count: "+ String.valueOf(count));
                    fillLayout(sessions);

                }


            }
        });


    }

    public void fillLayout(List<Session> sessions){
        HashMap<String,String> sessionInfo= new HashMap<>();

        for (int i=0;i<sessions.size();i++){
            sessionInfo.put("Workout "+String.valueOf(sessions.get(i).getId()),sessions.get(i).getDate_session()+"\n"+sessions.get(i).getMovements());
        }

        List<HashMap<String,String>> ListItems = new ArrayList<>();

        SimpleAdapter adapter= new SimpleAdapter(getApplicationContext(),ListItems,R.layout.list_item_statistics,
                new String[]{"First Line","Second Line"},
                new int[]{R.id.id_session,R.id.date_and_movements});

        Iterator it= sessionInfo.entrySet().iterator();

        while (it.hasNext()){
            HashMap<String,String> resultMap = new HashMap<>();
            Map.Entry pair = (Map.Entry)it.next();
            resultMap.put("First Line", pair.getKey().toString());
            resultMap.put("Second Line", pair.getValue().toString());
            ListItems.add(resultMap);
        }

        binding.listsessions.setAdapter(adapter);
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
