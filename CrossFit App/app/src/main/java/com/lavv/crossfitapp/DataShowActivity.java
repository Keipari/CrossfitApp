/**
 * This Activity class of the interaction with the user when viewing the statistics of a specific movement
 * in a given month.
 */
package com.lavv.crossfitapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.lavv.crossfitapp.databinding.ActivityDatashowBinding;
import com.lavv.crossfitapp.dblogic.DataBaseHelper;
import com.lavv.crossfitapp.dblogic.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
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
        initializeBaseValues(savedInstanceState);

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

        //When button clicked we display the retrieve data
        binding.GetEntries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (month!=" " && movement!=" "){
                    displayData();
                }
            }
        });
    }

    // Here we make the request of the information and display thhe results
    public void displayData(){
        DataBaseHelper dataBaseHelper= new DataBaseHelper(getApplicationContext());
        List<Session> sessions = dataBaseHelper.getSessionsByMonthMovements(month,movement);
        int count = sessions.size();
        binding.count.setText("Count: "+ String.valueOf(count));
        fillLayout(sessions);
    }

    /**
     * Here we fill the list we the retrieved data
     * @param sessions A list of type Session, containing all the sessions obtained from the request
     */
    public void fillLayout(List<Session> sessions){
        //Initializing the hashmap
        HashMap<String,String> sessionInfo=  new LinkedHashMap();
        StringBuffer sb;

        //Filling the sessioninfo hashmap with session IDs, session dates and movements
        for (int i=0;i<sessions.size();i++){
            sb= new StringBuffer(sessions.get(i).getMovements());
            sb.deleteCharAt(sb.length()-1);
            sessionInfo.put("Workout "+String.valueOf(sessions.get(i).getId()),sessions.get(i).getDate_session()+"\n"+sb);
        }

        //Initializing the list of hashmap
        List<HashMap<String,String>> ListItems = new ArrayList<>();

        //Creating the adapter
        SimpleAdapter adapter= new SimpleAdapter(getApplicationContext(),ListItems,R.layout.list_item_statistics,
                new String[]{"First Line","Second Line"},
                new int[]{R.id.id_session,R.id.date_and_movements});

        Iterator it= sessionInfo.entrySet().iterator();

        //Pairing
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

    /**
     * Here we initialize the data if the savedInstanceState is not null
     * @param savedInstanceState a bundle object that contains the saved data from a previus state
     */
    @SuppressLint("SetTextI18n")
    private void initializeBaseValues(Bundle savedInstanceState){
        if(savedInstanceState != null){
            month = savedInstanceState.getString("month");
            movement = savedInstanceState.getString("movement");
            displayData();
        }
    }
    /**
     *  Here we store the data from this state
     * @param outState Bundle object used to store the data
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("movement", movement);
        outState.putString("month", month);
        super.onSaveInstanceState(outState);
    }


}
