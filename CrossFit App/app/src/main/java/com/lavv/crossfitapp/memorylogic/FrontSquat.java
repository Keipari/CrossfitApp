/**
* This class has all the information about the Front Squat Exercise called in the ExerciseViewer class
*/

package com.lavv.crossfitapp.memorylogic;

public class FrontSquat extends Exercise {

    public FrontSquat(){
        this.description = "The front squat builds exactly on the mechanics of the air squat. All " +
                "that is added is a load supported in the front-rack position, where the weight " +
                "sits squarely on the upper chest and shoulders, and the elbows point forward to " +
                "bring the upper arms parallel to the floor. This “rack position,” critical to " +
                "weightlifting, both demands and improves wrist and shoulder flexibility while the " +
                "load, supported by the torso, both demands and improves midline stability.";

        this.video_ID = "uYumuL_G_V0";
    }
    /**
     *
     * @return This method returns the string description given to FrontSquat
     */
    @Override
    public String getDescription() {
        return description;
    }
    /**
     *
     * @return This method returns the video given to FrontSquat
     */
    @Override
    public String getVideo_ID() {
        return video_ID;
    }
}
