package com.lavv.crossfitapp;

public class AirSquat extends Exercise{

    /*
    This class has all the information about the Air Squat Exercise called in the ExerciseViewer class
    */

    AirSquat(){
        this.description = "The squat is a beautiful, natural movement. It demands midline " +
                "stabilization, posterior-chain engagement and core-to-extremity movement, and it " +
                "can be used to move your body weight or very large loads held in a variety of " +
                "positions. At one end of the spectrum, the squat is an essential component of " +
                "weightlifting and powerlifting, and at the other end, the squat is essential to " +
                "getting off a toilet seat. Regardless of what the problem is, the answer is to " +
                "squat.";

        this.video_ID = "rMvwVtlqjTE";
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
