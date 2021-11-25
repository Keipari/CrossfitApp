package com.lavv.crossfitapp;

public class Session {
    private int id;
    private String date_session;
    private String comment;
    private String movements;

    public Session(int id, String date_session, String comment, String movements) {
        this.id = id;
        this.date_session = date_session;
        this.comment = comment;
        this.movements = movements;
    }

    public String getMovements() {
        return movements;
    }

    public void setMovements(String movements) {
        this.movements = movements;
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
