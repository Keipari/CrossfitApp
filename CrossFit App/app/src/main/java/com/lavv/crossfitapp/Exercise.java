package com.lavv.crossfitapp;

public abstract class Exercise {
    protected String description;
    protected String video_ID;

    public abstract String getDescription();
    public abstract String getVideo_ID();

}
