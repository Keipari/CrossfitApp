/**
 * This class has all the information about the Push Press Exercise called in the ExerciseViewer class
 */
package com.lavv.crossfitapp.memorylogic;

public class PushPress extends Exercise {

    public PushPress(){
        this.description = "In the push press, the core-to-extremity principle is obvious as the " +
                "muscles of the power zone — including the hip flexors, hip extensors " +
                "(glutes and hams), spinal erectors, and quadriceps — assist the arms in driving " +
                "the barbell overhead. With the push press, you will be able to move overhead as " +
                "much as 30 percent more weight than with the shoulder press. Regular practice of " +
                "the push press — and the push jerk — develops power and speed, which are critical " +
                "to effective and efficient athletic movement.";

        this.video_ID = "iaBVSJm78ko";
    }

    /**
     *
     * @return
     * This method returns the description given to PushPress
     */
    @Override
    public String getVideo_ID() {
        return video_ID;
    }

    /**
     *
     * @return
     * This method returns the video given to PushPress
     */
    @Override
    public String getDescription() {
        return description;
    }
}
