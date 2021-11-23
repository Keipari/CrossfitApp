package com.lavv.crossfitapp;

public class Deadlift extends Exercise{

    Deadlift(){
        this.description = "The deadlift, being no more than the safe and sound approach by " +
                "which any object should be lifted from the ground, keeps company with standing, " +
                "running, jumping, and throwing for functionality but imparts quick and prominent " +
                "athletic advantage like no other exercise. It is unrivaled in its simplicity and " +
                "impact while unique in its capacity for increasing head-to-toe strength.";

        this.video_ID = "1ZXobu7JvvE";
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getVideo_ID() {
        return video_ID;
    }
}
