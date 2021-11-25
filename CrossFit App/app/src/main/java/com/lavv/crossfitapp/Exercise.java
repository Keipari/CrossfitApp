package com.lavv.crossfitapp;


/*
This abstract class is used as father of all the other exercises to have a format of properties and
methods that we only need to override in the specified exercise
*/

public abstract class Exercise {
    protected String description;
    protected String video_ID;

    public abstract String getDescription();
    public abstract String getVideo_ID();

}
