package com.lavv.crossfitapp;

public class PushJerk extends Exercise{

    /*
    This class has all the information about the Push Jerk Exercise called in the ExerciseViewer class
    */

    PushJerk(){
        this.description = "With the push jerk, you will be able to move overhead as much as 30 " +
                "percent more weight than with the push press. Similar to the push press, the push " +
                "jerk employs the hips to create upward momentum on the bar, but the athlete then " +
                "pushes against the bar with the arms and dips a second time to receive the push " +
                "jerk in a partial squat. With the arms locked out, the legs complete the lift. " +
                "After mastering the push jerk, you will find that it will unconsciously displace " +
                "the push press as your method of choice when going overhead.";

        this.video_ID = "VrHNJXoSyXw";
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
