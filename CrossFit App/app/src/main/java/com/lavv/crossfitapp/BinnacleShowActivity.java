/**
 * This Activity class takes care of the interaction with the user when viewing the general information
 * of all the register sessions.
 *
 * Here the display of all sessions in a List is handled, which is why it implements the
 * AdapterView.OnItemClickListener interface that helps with this task.
 */
package com.lavv.crossfitapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.lavv.crossfitapp.databinding.ActivityBinnacleshowBinding;
import com.lavv.crossfitapp.dblogic.DataBaseHelper;
import com.lavv.crossfitapp.dblogic.Session;
import java.util.ArrayList;
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

        //Setting the ListView listener
        binding.list.setOnItemClickListener(this);

        //Setting the Button listener, When this button is pressed it takes us to the main activity
        binding.goToMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * Here we establish the action to do when an Item is pressed in the list
     * @param adapterView The AdapterView where the click happened
     * @param view The view within the AdapterView that was clicked
     * @param i The position of the view in the adapter
     * @param l The row id of the item that was clicked
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //Getting the instance  DataBaseHelper object that helps us with the transactions with the database
        DataBaseHelper dataBaseHelper= new DataBaseHelper(getApplicationContext());
        //Getting the String of the id of the cliked Item
        String sessioninfo = adapterView.getItemAtPosition(i).toString();
        String id = sessioninfo.substring(sessioninfo.lastIndexOf(" ") + 1);
        StringBuffer id_b= new StringBuffer(id);
        id_b.deleteCharAt(id_b.length()-1);
        //Getting the session by id
        Session session = dataBaseHelper.getSessionById(String.valueOf(id_b));
        //Passing the Session object through an intent and starting the SessionDetailsActivity
        Intent intent = new Intent(getApplicationContext(), SessionDetailsActivity.class);
        intent.putExtra("session", session);
        startActivity(intent);
    }

    /**
     *
     * @param sessions A list of type Session, containing all the registered session
     */
    public void fillLayout(List<Session> sessions){
        //Filling the sessioninfo hashmap with session IDs and session dates
        String cadena="";
        for (int i=0;i<sessions.size();i++){
            sessionInfo.put("Workout "+String.valueOf(sessions.get(i).getId()),sessions.get(i).getDate_session());
            cadena= cadena+sessions.get(i).getDate_session()+" ";
        }
        //Initializing the list of hashmap
        List<HashMap<String,String>> ListItems = new ArrayList<>();

        //Creating the adapter
        SimpleAdapter adapter= new SimpleAdapter(getApplicationContext(),ListItems,R.layout.list_item,
                new String[]{"First Line","Second Line"},
                new int[]{R.id.text1,R.id.text2});

        Iterator it= sessionInfo.entrySet().iterator();

        //Pairing
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