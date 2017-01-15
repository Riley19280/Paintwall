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


public class gameModeSelectorActivity extends Activity {
    RelativeLayout relativeLayout;
    private final int MARGIN = 5;

    int width = (int) (GM.getScreenDims().x * .8f);
    int height = (int) (GM.getScreenDims().y * .8f) / 5;

    MediaPlayer mp;


    @SuppressWarnings("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game_mode_selector);

        relativeLayout = (RelativeLayout) findViewById(R.id.relLayout);

        Button a = new Button(this);
        a.setId(1);
        Button b = new Button(this);
        b.setId(2);
        Button c = new Button(this);
        c.setId(3);
        Button d = new Button(this);
        d.setId(4);
        Button e = new Button(this);
        d.setId(5);


        addButtonB(b);
        addButtonA(a, b);
        addButtonC(c, b);
        addButtonD(d, c);
        addButtonE(e, a);

        a.setText("NORMAL");
        b.setText("ENDLESS");
        c.setText("HARDCORE");
        d.setText("FLAWLESS");
        e.setText("EASY");

        mp = MediaPlayer.create(getApplicationContext(), R.raw.click);

    }


    private void addButtonA(Button a, Button b) {
        a.setWidth(width);
        a.setHeight(height);
        a.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent i = new Intent(gameModeSelectorActivity.this, GameActivity.class);
                startActivity(i);
                GM.GameMode = GM.GAMEMODE.NORMAL;
                playSound();
                finish();
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
                Intent i = new Intent(gameModeSelectorActivity.this, GameActivity.class);
                startActivity(i);
                GM.GameMode = GM.GAMEMODE.ENDLESS;
                playSound();
                finish();
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
                GM.GameMode = GM.GAMEMODE.HARDCORE;
                Intent i = new Intent(gameModeSelectorActivity.this, GameActivity.class);
                startActivity(i);
                playSound();
                finish();
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
                Intent i = new Intent(gameModeSelectorActivity.this, GameActivity.class);
                startActivity(i);
                GM.GameMode = GM.GAMEMODE.FLAWLESS;
                playSound();
                finish();


            }
        });

        RelativeLayout.LayoutParams btnDetails = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        btnDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        btnDetails.addRule(RelativeLayout.BELOW, c.getId());
        btnDetails.setMargins(0, MARGIN, 0, 0);

        relativeLayout.addView(d, btnDetails);

    }

    private void addButtonE(Button e, Button a) {
        e.setWidth(width);
        e.setHeight(height);
        e.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent i = new Intent(gameModeSelectorActivity.this, GameActivity.class);
                startActivity(i);
                GM.GameMode = GM.GAMEMODE.EASY;
                playSound();
                finish();


            }
        });
        RelativeLayout.LayoutParams btnDetails = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        btnDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        btnDetails.addRule(RelativeLayout.ABOVE, a.getId());
        btnDetails.setMargins(0, 0, 0, MARGIN);

        relativeLayout.addView(e, btnDetails);

    }


    private void playSound() {
        SharedPreferences settings = getSharedPreferences(GM.PREFS_NAME, 0);
//mp = MediaPlayer.create(getApplicationContext(), R.raw.click);
        if (settings.getBoolean("sound", true)) {
            mp.start();
        }
    }
}
