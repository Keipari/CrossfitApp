package com.lavv.crossfitapp.DB;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import java.util.Date;

@Entity(primaryKeys = {"Fecha", "Hora"})
public class Db {
    @NonNull
    public String Fecha;
    @NonNull
    public String Hora;
    public boolean act1;
    public boolean act2;
    public boolean act3;
    public boolean act4;
    public boolean act5;
    public boolean act6;
    public boolean act7;
    public String Comment;

    public void setFecha(String fecha) {
        this.Fecha = fecha;
    }

    public void setHora(String hora) {
        this.Hora = hora;
    }
    public void setComment(String comment) {
        this.Comment = comment;
    }
    public  void setAct1(boolean first){
        this.act1 = first;
    }
    public  void setAct2(boolean second){
        this.act2 = second;
    }
    public  void setAct3(boolean thirt){
        this.act3 = thirt;
    }
    public  void setAct4(boolean four){
        this.act4 = four;
    }
    public  void setAct5(boolean five){
        this.act5 = five;
    }
    public  void setAct6(boolean six){
        this.act6 = six;
    }
    public  void setAct7(boolean seven){
        this.act7 = seven;
    }

    public String getFecha() {
        return Fecha;
    }
    public String getHora() {
        return Hora;
    }
    public String getComment() {
        return Comment;
    }
    public  boolean getAct1(){
        return act1;
    }
    public  boolean getAct2(){
        return act2;
    }
    public  boolean getAct3(){
        return act3;
    }
    public  boolean getAct4(){
        return act4;
    }
    public  boolean getAct5(){
        return act5;
    }
    public  boolean getAct6(){
        return act6;
    }
    public  boolean getAct7(){
        return act7;
    }



}
