/**
 * This Activity class of the interaction with the user when viewing the details of a specific movement/exercise.
 * Here the display of all the information of a movement is handled.
 * Third-party libraries were used to manage videos.
 */
package com.lavv.crossfitapp;

import static com.lavv.crossfitapp.ExerciseSelector.TYPE_OF_EXERCISE;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.lavv.crossfitapp.databinding.ActivityExerciseBinding;
import com.lavv.crossfitapp.memorylogic.AirSquat;
import com.lavv.crossfitapp.memorylogic.Deadlift;
import com.lavv.crossfitapp.memorylogic.FrontSquat;
import com.lavv.crossfitapp.memorylogic.OverheadSquat;
import com.lavv.crossfitapp.memorylogic.PushJerk;
import com.lavv.crossfitapp.memorylogic.PushPress;
import com.lavv.crossfitapp.memorylogic.ShoulderPress;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class ExerciseViewer extends AppCompatActivity {
    private ActivityExerciseBinding binding;
    private String videoID;
    private int type;
    private YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExerciseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupFullScreen();
        //Getting the exercise type
        if(savedInstanceState != null){
            type = savedInstanceState.getInt(TYPE_OF_EXERCISE);
        }else{
            Intent intent = getIntent();
            type = intent.getIntExtra(TYPE_OF_EXERCISE, 0 );
        }
        //Initializing layout
        layoutInitializer();
        youTubePlayerView = binding.ExerciseVideo;
        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(videoListener);
        youTubePlayerView.addFullScreenListener(fullScreenListener);

        //Setting the Button listener, When this button is pressed it takes us to the main activity
        binding.goToMainMenu.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
        //Setting the Button listener, When this button is pressed it takes us to the ExerciseSelector
        binding.toMovementList.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ExerciseSelector.class);
            startActivity(intent);
        });

    }

    //listener when full screen
    YouTubePlayerFullScreenListener fullScreenListener = new YouTubePlayerFullScreenListener() {
        @Override
        public void onYouTubePlayerEnterFullScreen() {
            YouTubePlayerView youtube = binding.ExerciseVideo;
            binding.videoContainer.removeViewInLayout(youtube);
            binding.getRoot().addView(youtube);
            binding.toMovementList.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onYouTubePlayerExitFullScreen() {
            YouTubePlayerView youtube = binding.ExerciseVideo;
            binding.getRoot().removeViewInLayout(youtube);
            binding.videoContainer.addView(youtube);
            binding.toMovementList.setVisibility(View.VISIBLE);
        }
    };

    //Listener to play the video
    AbstractYouTubePlayerListener videoListener = new AbstractYouTubePlayerListener(){
        @Override
        public void onReady(YouTubePlayer youTubePlayer) {
            super.onReady(youTubePlayer);
            youTubePlayer.loadVideo(videoID, 0);
        }

        @Override
        public void onError(YouTubePlayer youTubePlayer, PlayerConstants.PlayerError error) {
            super.onError(youTubePlayer, error);
            youTubePlayer.loadVideo(videoID, 0);
        }

        @Override
        public void onStateChange(YouTubePlayer youTubePlayer, PlayerConstants.PlayerState state) {
            super.onStateChange(youTubePlayer, state);
            if(state == state.ENDED){
                youTubePlayer.loadVideo(videoID, 0);
            }
            if(state == state.UNKNOWN){
                youTubePlayer.loadVideo(videoID, 0);
            }

        }
    };

    //Depending on the case provided, it shows the information of the selected exercise
    private void layoutInitializer(){
        switch(type){
            case 0:
                AirSquat airSquat = new AirSquat();
                binding.ExerciseTitle.setText("Air Squat");
                binding.Description.setText(airSquat.getDescription());
                videoID = airSquat.getVideo_ID();
                break;
            case 1:
                FrontSquat frontSquat = new FrontSquat();
                binding.ExerciseTitle.setText("Front Squat");
                binding.Description.setText(frontSquat.getDescription());
                videoID = frontSquat.getVideo_ID();
                break;
            case 2:
                OverheadSquat overheadSquat = new OverheadSquat();
                binding.ExerciseTitle.setText("Overhead Squat");
                binding.Description.setText(overheadSquat.getDescription());
                videoID = overheadSquat.getVideo_ID();
                break;
            case 3:
                ShoulderPress shoulderPress = new ShoulderPress();
                binding.ExerciseTitle.setText("Shoulder Press");
                binding.Description.setText(shoulderPress.getDescription());
                videoID = shoulderPress.getVideo_ID();
                break;
            case 4:
                PushPress pushPress = new PushPress();
                binding.ExerciseTitle.setText("Push Press");
                binding.Description.setText(pushPress.getDescription());
                videoID = pushPress.getVideo_ID();
                break;
            case 5:
                PushJerk pushJerk = new PushJerk();
                binding.ExerciseTitle.setText("Push Jerk");
                binding.Description.setText(pushJerk.getDescription());
                videoID = pushJerk.getVideo_ID();
                break;
            case 6:
                Deadlift deadlift = new Deadlift();
                binding.ExerciseTitle.setText("Deadlift");
                binding.Description.setText(deadlift.getDescription());
                videoID = deadlift.getVideo_ID();
                break;
            default:
                break;
        }
    }

    //Set full screen
    private void setupFullScreen() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    /**
     *  Here we store the data from this state
     * @param outState Bundle object used to store the data
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(TYPE_OF_EXERCISE, type);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        youTubePlayerView.release();
        super.onDestroy();
    }
}
