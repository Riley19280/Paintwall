package xyz.triangularcubicle.paintwall;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class pauseActivity extends Activity {
    RelativeLayout relativeLayout;
    private final int MARGIN = 5;

    int width = (int) (GM.getScreenDims().x * .8f);
    int height = (int) (GM.getScreenDims().y * .8f) / 5;

    MediaPlayer mp;

    InterstitialAd mInterstitialAd;

    @SuppressWarnings("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pause);
        relativeLayout = (RelativeLayout) findViewById(R.id.relLayout);

        GM.getWinManager().pauseTimer();

        Button a = new Button(this);
        a.setId(1);
        Button b = new Button(this);
        b.setId(2);
        Button c = new Button(this);
        c.setId(3);
        Button d = new Button(this);
        d.setId(4);

        addButtonB(b);
        addButtonA(a, b);
        addButtonC(c, b);
        addButtonD(d, c);

        a.setText("RESUME");
        b.setText("RESTART");
        c.setText("OPTIONS");
        d.setText("MAIN MENU");

        mp = MediaPlayer.create(getApplicationContext(), R.raw.click);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.ad_PauseInt));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                // beginPlayingGame();
            }
        });

        requestNewInterstitial();
    }


    private void addButtonA(Button a, Button b) {
        a.setWidth(width);
        a.setHeight(height);
        a.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                finish();
                GM.getWinManager().resumeTimer();
                playSound();
            }
        });

        RelativeLayout.LayoutParams btnDetails = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        btnDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        btnDetails.addRule(RelativeLayout.ABOVE, b.getId());
        btnDetails.setMargins(0, 0, 0, MARGIN);

        relativeLayout.addView(a, btnDetails);
    }

    private void addButtonB(Button b) {
        b.setWidth(width);
        b.setHeight(height);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                GM.getWinManager().pauseTimer(); //resets it
                GM.NEWGAME();
                finish();

                ShowAD();
            }
        });

        RelativeLayout.LayoutParams btnDetails = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        btnDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        btnDetails.addRule(RelativeLayout.CENTER_VERTICAL);

        relativeLayout.addView(b, btnDetails);
    }

    private void addButtonC(Button c, Button b) {
        c.setWidth(width);
        c.setHeight(height);
        c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent i = new Intent(pauseActivity.this, optionsActivity.class);
                startActivity(i);
                playSound();
            }
        });

        RelativeLayout.LayoutParams btnDetails = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        btnDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        btnDetails.addRule(RelativeLayout.BELOW, b.getId());
        btnDetails.setMargins(0, MARGIN, 0, 0);

        relativeLayout.addView(c, btnDetails);
    }

    private void addButtonD(Button d, Button c) {
        d.setWidth(width);
        d.setHeight(height);
        d.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                finish();
                Intent i = new Intent(pauseActivity.this, MainMenuActivity.class);
                startActivity(i);

                if (GM.gameActivity != null)
                    GM.gameActivity.finish();
                ;
                ShowAD();
            }
        });

        RelativeLayout.LayoutParams btnDetails = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        btnDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        btnDetails.addRule(RelativeLayout.BELOW, c.getId());
        btnDetails.setMargins(0, MARGIN, 0, 0);

        relativeLayout.addView(d, btnDetails);

    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        mInterstitialAd.loadAd(adRequest);
    }

    void ShowAD() {
        SharedPreferences settings = getSharedPreferences(GM.PREFS_NAME, 0);
        if (settings.getBoolean("ads", true))
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }

    }


    private void playSound() {
        SharedPreferences settings = getSharedPreferences(GM.PREFS_NAME, 0);
//MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.click);
        if (settings.getBoolean("sound", true)) {
            mp.start();
        }
    }
}
