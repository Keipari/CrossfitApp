package com.lavv.crossfitapp;

public class Motion {
    private int id;
    private String name;

    public Motion(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
