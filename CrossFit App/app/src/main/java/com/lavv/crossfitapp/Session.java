package com.lavv.crossfitapp;

public class Session {
    private int id;
    private String date_session;
    private String comment;

    public Session(int id, String date_session, String comment) {
        this.id = id;
        this.date_session = date_session;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_session() {
        return date_session;
    }

    public void setDate_session(String date_session) {
        this.date_session = date_session;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
