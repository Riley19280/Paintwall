package xyz.triangularcubicle.paintwall;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import java.util.Random;

public class GameActivity extends Activity {

    RelativeLayout relLayout;

    GameRoot gameRoot;
    SharedPreferences settings;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);
        relLayout = (RelativeLayout) findViewById(R.id.relLayout);

        //create objects (entry point for game)

        gameRoot = new GameRoot(this.getApplicationContext());
        gameRoot.resume();
        GM.gameActivity = this;

        settings = getSharedPreferences(GM.PREFS_NAME, 0);
        Log.i("betterinf", "SOUND IS : " + Boolean.toString(settings.getBoolean("sound", true)));
        Log.i("betterinf", "MUSIC IS : " + Boolean.toString(settings.getBoolean("music", true)));

        Random r = new Random();
        int t =Math.abs(r.nextInt() % 4);
        switch (t) {
            case 0:
                mp = MediaPlayer.create(getApplicationContext(), R.raw.hereagain);

                break;
            case 1:
                mp = MediaPlayer.create(getApplicationContext(), R.raw.getreal);

                break;
            case 2:
                mp = MediaPlayer.create(getApplicationContext(), R.raw.intotheden);

                break;
            case 3:
                mp = MediaPlayer.create(getApplicationContext(), R.raw.themission);

                break;
        }
        try {
            mp.setLooping(true);
            playMusic();
        } catch (Exception e) {
            Log.i("betterinf", "MUSIC IS : " + t);

        }
    }


    long back_click_1;
    long timeBetween = 400000000;

    @Override
    public void onBackPressed() {

        if (System.nanoTime() - back_click_1 <= timeBetween) {
            Intent i = new Intent(this, pauseActivity.class);

            startActivity(i);
        } else {
            back_click_1 = System.nanoTime();

        }
    }

    @Override
    protected void onResume() {
        gameRoot.resume();
        GM.getWinManager().resumeTimer();
        playMusic();
        super.onResume();
    }

    @Override
    protected void onPause() {
        gameRoot.pause();
        if (GM.GameMode != GM.GAMEMODE.ENDLESS)
            GM.getWinManager().pauseTimer();
        if (settings.getBoolean("music", true))
            mp.pause();

        super.onPause();
    }

    private void playMusic() {
        if (settings.getBoolean("music", true)) {
            mp.start();
        }
    }

}
