package com.lavv.crossfitapp;

public class ShoulderPress extends Exercise{

    ShoulderPress(){
        this.description = "Never is the stabilizing role of the abdominals more critical than " +
                "when attempting to drive loads overhead. We train our athletes to think of every " +
                "exercise as an ab exercise. This is essential in the overhead lifts. It is easy " +
                "to see when an athlete is not sufficiently engaging the abs in an overhead " +
                "press—the body arches so as to push the hips, pelvis, and stomach ahead of the " +
                "bar. Constant vigilance is required of every lifter to prevent and correct this " +
                "postural deformation.";

        this.video_ID = "5yWaNOvgFCM";
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
