package com.lavv.crossfitapp;

public class OverheadSquat extends Exercise{

    OverheadSquat(){
        this.description = "The overhead squat is the ultimate core exercise and peerless in " +
                "developing effective athletic movement. This functional gem trains for efficient " +
                "transfer of energy from large to small body parts and improves functional " +
                "flexibility. Similarly, it develops the squat by amplifying and cruelly punishing " +
                "faults in posture, movement, and stability. The overhead squat is to midline " +
                "control, stability and balance what the clean and snatch are to power—unsurpassed.";

        this.video_ID = "pn8mqlG0nkE";
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
