package com.lavv.crossfitapp;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Session {
    private Date date_session;
    private String only_Date;
    private String only_Time;
    private String comment;
    private boolean[] movements;

    @SuppressLint("SimpleDateFormat")
    public Session(String date_session, String hour_session , String comment, boolean[] movements) {
        String date = date_session + " " + hour_session;
        try{
            this.date_session = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(date);
            //this.hour_session = new SimpleDateFormat("hh:mm").parse(hour_session);
            Log.i("Date", this.date_session.toString());
        }catch(Exception e){
            Log.i("Date", "it is not a valid date");
        }
        this.only_Date = date_session;
        this.only_Time = hour_session;
        this.comment = comment;
        this.movements = movements;
    }

    public String getComment() {
        return comment;
    }

    public Date getDate() { return date_session; }

    public String getMovements() {
        StringBuilder movementsConcatenate = new StringBuilder();

        for(int i = 0; i < movements.length; i++){
            switch(i){
                case 0:
                    if(movements[0]){
                        movementsConcatenate.append("Air Squat\n");
                    }
                    break;
                case 1:
                    if(movements[1]){
                        movementsConcatenate.append("Front Squat\n");
                    }
                    break;
                case 2:
                    if(movements[2]){
                        movementsConcatenate.append("Overhead Squat\n");
                    }
                    break;
                case 3:
                    if(movements[3]){
                        movementsConcatenate.append("Shoulder Press\n");
                    }
                    break;
                case 4:
                    if(movements[4]){
                        movementsConcatenate.append("Push Press\n");
                    }
                    break;
                case 5:
                    if(movements[5]){
                        movementsConcatenate.append("Push Jerk\n");
                    }
                    break;
                case 6:
                    if(movements[6]){
                        movementsConcatenate.append("Deadlift\n");
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + i);
            }
        }

        return movementsConcatenate.toString();
    }

    public String getOnlyTime() { return only_Time; }

    public String getOnlyDate() { return only_Date; }
}
