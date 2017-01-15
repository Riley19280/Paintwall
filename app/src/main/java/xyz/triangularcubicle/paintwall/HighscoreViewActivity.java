package xyz.triangularcubicle.paintwall;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.RelativeLayout;

/**
 * Created by riley on 6/12/2016.
 */
public class HighscoreViewActivity extends AppCompatActivity {
    RelativeLayout relativeLayout;
    private final int MARGIN = 0;

    int width = (int) (GM.getScreenDims().x * .8f);
    int height = (int) (GM.getScreenDims().y * .8f) / 5;

    MediaPlayer mp;


    @SuppressWarnings("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("Highscores");
        setContentView(R.layout.activity_view_highscore);

        relativeLayout = (RelativeLayout) findViewById(R.id.relLayout);

        int textsize = 20;

        TextView a = new TextView(this);
        a.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textsize);
        a.setId(1);
        TextView b = new TextView(this);
        b.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textsize);
        b.setId(2);
        TextView c = new TextView(this);
        c.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textsize);
        c.setId(3);
        TextView d = new TextView(this);
        d.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textsize);
        d.setId(4);
        TextView e = new TextView(this);
        e.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textsize);
        e.setId(5);

        Button btn = new Button(this);
        btn.setText("Back");
        btn.setId(6);

        addTextViewB(b);
        addTextViewA(a, b);
        addTextViewC(c, b);
        addTextViewD(d, c);
        addTextViewE(e, a);
        addBackButton(btn);


        a.setText("NORMAL: " + HighscoreManager.getHighscore(GM.GAMEMODE.NORMAL, this));
        b.setText("ENDLESS: "+HighscoreManager.getHighscore(GM.GAMEMODE.ENDLESS, this));
        c.setText("HARDCORE: "+HighscoreManager.getHighscore(GM.GAMEMODE.HARDCORE, this));
        d.setText("FLAWLESS:"+HighscoreManager.getHighscore(GM.GAMEMODE.FLAWLESS, this));
        e.setText("EASY :"+HighscoreManager.getHighscore(GM.GAMEMODE.EASY, this));

        mp = MediaPlayer.create(getApplicationContext(), R.raw.click);

    }


    private void addTextViewA(TextView a, TextView b) {
        a.setWidth(width);
        a.setHeight(height);

        RelativeLayout.LayoutParams txtDetail = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        txtDetail.addRule(RelativeLayout.CENTER_IN_PARENT);
        txtDetail.addRule(RelativeLayout.ABOVE, b.getId());
        txtDetail.setMargins(0, 0, 0, MARGIN);

        relativeLayout.addView(a, txtDetail);

    }

    private void addTextViewB(TextView b) {
        b.setWidth(width);
        b.setHeight(height);

        RelativeLayout.LayoutParams txtDetail = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        txtDetail.addRule(RelativeLayout.CENTER_IN_PARENT);
        txtDetail.addRule(RelativeLayout.CENTER_IN_PARENT);

        relativeLayout.addView(b, txtDetail);

    }

    private void addTextViewC(TextView c, TextView b) {
        c.setWidth(width);
        c.setHeight(height);

        RelativeLayout.LayoutParams txtDetail = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        txtDetail.addRule(RelativeLayout.CENTER_IN_PARENT);
        txtDetail.addRule(RelativeLayout.BELOW, b.getId());
        txtDetail.setMargins(0, MARGIN, 0, 0);

        relativeLayout.addView(c, txtDetail);
    }

    private void addTextViewD(TextView d, TextView c) {
        d.setWidth(width);
        d.setHeight(height);

        RelativeLayout.LayoutParams txtDetail = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        txtDetail.addRule(RelativeLayout.CENTER_IN_PARENT);
        txtDetail.addRule(RelativeLayout.BELOW, c.getId());
        txtDetail.setMargins(0, MARGIN, 0, 0);

        relativeLayout.addView(d, txtDetail);

    }

    private void addTextViewE(TextView e, TextView a) {
        e.setWidth(width);
        e.setHeight(height);

        RelativeLayout.LayoutParams txtDetail = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        txtDetail.addRule(RelativeLayout.CENTER_IN_PARENT);
        txtDetail.addRule(RelativeLayout.ABOVE, a.getId());
        txtDetail.setMargins(0, 0, 0, MARGIN);

        relativeLayout.addView(e, txtDetail);

    }

    private void addBackButton(Button btn) {
        btn.setWidth((int)(width/1.5));
        btn.setHeight((int)(height/1.5));
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
               finish();

            }
        });

        RelativeLayout.LayoutParams btnDetail = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        btnDetail.addRule(RelativeLayout.CENTER_HORIZONTAL);
        btnDetail.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        btnDetail.setMargins(0, 0, 0, 7);

        relativeLayout.addView(btn, btnDetail);

    }

    private void playSound() {
        SharedPreferences settings = getSharedPreferences(GM.PREFS_NAME, 0);
//mp = MediaPlayer.create(getApplicationContext(), R.raw.click);
        if (settings.getBoolean("sound", true)) {
            mp.start();
        }
    }

    }
