package com.lavv.crossfitapp;

public class PushPress extends Exercise{

    PushPress(){
        this.description = "In the push press, the core-to-extremity principle is obvious as the " +
                "muscles of the power zone — including the hip flexors, hip extensors " +
                "(glutes and hams), spinal erectors, and quadriceps — assist the arms in driving " +
                "the barbell overhead. With the push press, you will be able to move overhead as " +
                "much as 30 percent more weight than with the shoulder press. Regular practice of " +
                "the push press — and the push jerk — develops power and speed, which are critical " +
                "to effective and efficient athletic movement.";

        this.video_ID = "iaBVSJm78ko";
    }

    @Override
    public String getVideo_ID() {
        return video_ID;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
