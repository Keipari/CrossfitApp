/**
 *
 *  This class has all the information about the Air Squat Exercise called in the ExerciseViewer class
 */
package com.lavv.crossfitapp.memorylogic;

public class AirSquat extends Exercise {



    public AirSquat(){
        this.description = "The squat is a beautiful, natural movement. It demands midline " +
                "stabilization, posterior-chain engagement and core-to-extremity movement, and it " +
                "can be used to move your body weight or very large loads held in a variety of " +
                "positions. At one end of the spectrum, the squat is an essential component of " +
                "weightlifting and powerlifting, and at the other end, the squat is essential to " +
                "getting off a toilet seat. Regardless of what the problem is, the answer is to " +
                "squat.";

        this.video_ID = "rMvwVtlqjTE";
    }

    /**
     *
     * @return
     * This method returns the video given to AirSquat
     */
    @Override
    public String getVideo_ID() {
        return video_ID;
    }

    /**
     *
     * @return
     * This method returns the description given to AirSquat
     */
    @Override
    public String getDescription() {
        return description;
    }
}
