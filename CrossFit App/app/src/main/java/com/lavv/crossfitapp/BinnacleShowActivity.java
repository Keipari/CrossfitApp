package com.lavv.crossfitapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lavv.crossfitapp.databinding.ActivityBinnacleshowBinding;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BinnacleShowActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ActivityBinnacleshowBinding binding;
    private HashMap<String,String> sessionInfo=  new LinkedHashMap();;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBinnacleshowBinding.inflate(getLayoutInflater());
        setupFullScreen();
        setContentView(binding.getRoot());

        DataBaseHelper dataBaseHelper= new DataBaseHelper(getApplicationContext());
        List<Session> sessions = dataBaseHelper.getAllSessions();
        fillLayout(sessions);

        binding.list.setOnItemClickListener(this);


        binding.goToMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        DataBaseHelper dataBaseHelper= new DataBaseHelper(getApplicationContext());
        String sessioninfo = adapterView.getItemAtPosition(i).toString();
        String id = sessioninfo.substring(sessioninfo.lastIndexOf(" ") + 1);
        StringBuffer id_b= new StringBuffer(id);
        id_b.deleteCharAt(id_b.length()-1);
        Session session = dataBaseHelper.getSessionById(String.valueOf(id_b));
        Intent intent = new Intent(getApplicationContext(), SessionDetailsActivity.class);
        intent.putExtra("session", session);
        startActivity(intent);
    }

    public void fillLayout(List<Session> sessions){
        String cadena="";
        for (int i=0;i<sessions.size();i++){
            sessionInfo.put("Workout "+String.valueOf(sessions.get(i).getId()),sessions.get(i).getDate_session());
            cadena= cadena+sessions.get(i).getDate_session()+" ";
        }
        Log.i("medir",sessionInfo.toString());
        List<HashMap<String,String>> ListItems = new ArrayList<>();

        SimpleAdapter adapter= new SimpleAdapter(getApplicationContext(),ListItems,R.layout.list_item,
                new String[]{"First Line","Second Line"},
                new int[]{R.id.text1,R.id.text2});

        Iterator it= sessionInfo.entrySet().iterator();


        while (it.hasNext()){
            HashMap<String,String> resultMap = new HashMap<>();
            Map.Entry pair = (Map.Entry)it.next();
            resultMap.put("First Line", pair.getKey().toString());
            resultMap.put("Second Line", pair.getValue().toString());
            ListItems.add(resultMap);
        }

        binding.list.setAdapter(adapter);
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