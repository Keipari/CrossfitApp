/**
 * This class has all the information about the Overhead Squat Exercise called in the ExerciseViewer class
 */

package com.lavv.crossfitapp.memorylogic;

public class OverheadSquat extends Exercise {

    public OverheadSquat(){
        this.description = "The overhead squat is the ultimate core exercise and peerless in " +
                "developing effective athletic movement. This functional gem trains for efficient " +
                "transfer of energy from large to small body parts and improves functional " +
                "flexibility. Similarly, it develops the squat by amplifying and cruelly punishing " +
                "faults in posture, movement, and stability. The overhead squat is to midline " +
                "control, stability and balance what the clean and snatch are to power—unsurpassed.";

        this.video_ID = "pn8mqlG0nkE";
    }

    /**
     *
     * @return
     * This method returns the video given to OverheadSquat
     */
    @Override
    public String getVideo_ID() {
        return video_ID;
    }

    /**
     *
     * @return
     * This method returns the description given to OverheadSquat
     */
    @Override
    public String getDescription() {
        return description;
    }
}
