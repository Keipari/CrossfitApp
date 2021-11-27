/**
 * This abstract class is used as father of all the other exercises to have a format of properties and
 * methods that we only need to override in the specified exercise
 */
package com.lavv.crossfitapp.memorylogic;

public abstract class Exercise {
    protected String description;
    protected String video_ID;
    /**
     *
     * @return
     * This public abstract strings get the description for the exercise
     */
    public abstract String getDescription();

    /**
     *
     * @return
     * This public abstract strings get the video for the exercise
     */
    public abstract String getVideo_ID();

}
