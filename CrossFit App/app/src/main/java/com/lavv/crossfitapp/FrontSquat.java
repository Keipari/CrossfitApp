package com.lavv.crossfitapp;

public class FrontSquat extends Exercise{

    FrontSquat(){
        this.description = "The front squat builds exactly on the mechanics of the air squat. All " +
                "that is added is a load supported in the front-rack position, where the weight " +
                "sits squarely on the upper chest and shoulders, and the elbows point forward to " +
                "bring the upper arms parallel to the floor. This “rack position,” critical to " +
                "weightlifting, both demands and improves wrist and shoulder flexibility while the " +
                "load, supported by the torso, both demands and improves midline stability.";

        this.video_ID = "uYumuL_G_V0";
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
