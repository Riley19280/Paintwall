package xyz.triangularcubicle.paintwall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class MainMenuActivity extends Activity {

    RelativeLayout relLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_menu);

        relLayout = (RelativeLayout) findViewById(R.id.relLayout);


        SharedPreferences settings = getSharedPreferences(GM.PREFS_NAME, 0);
        if (settings.getBoolean("firstTime", true)) {
            Log.i("betterinf", "First Time Being Launched");


            settings.edit().putBoolean("sound", true).apply();
            settings.edit().putBoolean("music", true).apply();
            settings.edit().putInt("highscore_easy", 0).apply();
            settings.edit().putBoolean("ads", true).apply();
            settings.edit().putBoolean("firstTime", false).apply();
        }

        initializeScreenDims();

        registerCallbacks();
        if (settings.getBoolean("ads", true)) {
            AdView mAdView = (AdView) findViewById(R.id.ad_view);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }



    }

    private void registerCallbacks() {
        //touch anywhere
        relLayout.setOnTouchListener(
                new RelativeLayout.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN)
                            ToGame();
                        return true;
                    }

                    private void ToGame() {
                        Intent i = new Intent(MainMenuActivity.this, gameModeSelectorActivity.class);
                        startActivity(i);
                    }
                });


        ImageButton btnHelp = (ImageButton) findViewById(R.id.btnHelp);
        btnHelp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent i = new Intent(MainMenuActivity.this, helpActivity.class);
                startActivity(i);

            }
        });

        ImageButton btnSettings = (ImageButton) findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent i = new Intent(MainMenuActivity.this, optionsActivity.class);
                startActivity(i);

            }
        });

        Button btnViewHighscores = (Button) findViewById(R.id.btnViewHighscores);
        btnViewHighscores.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent i = new Intent(MainMenuActivity.this, HighscoreViewActivity.class);
                startActivity(i);

            }
        });
    }

    private void initializeScreenDims() {
        Point screenDims = new Point();
        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getSize(screenDims);
        Log.i("betterinf", "Screen Dimensions: " + screenDims.toString());
        GM.setScreenDims(screenDims);
    }


}
