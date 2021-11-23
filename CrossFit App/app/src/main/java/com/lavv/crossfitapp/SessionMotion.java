package com.lavv.crossfitapp;

public class SessionMotion {
    private int id_Session;
    private int id_Motion;

    public SessionMotion(int id_Session, int id_Motion) {
        this.id_Session = id_Session;
        this.id_Motion = id_Motion;
    }

    public int getId_Session() {
        return id_Session;
    }

    public void setId_Session(int id_Session) {
        this.id_Session = id_Session;
    }

    public int getId_Motion() {
        return id_Motion;
    }

    public void setId_Motion(int id_Motion) {
        this.id_Motion = id_Motion;
    }
}
