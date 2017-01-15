package xyz.triangularcubicle.paintwall;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;


public class optionsActivity extends AppCompatActivity {
    Switch sSound;
    Switch sMusic;
    SharedPreferences settings;

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        getSupportActionBar().setTitle("Options");
        getSupportActionBar().setIcon(R.mipmap.paintwall);

        settings = getSharedPreferences(GM.PREFS_NAME, 0);

        sSound = (Switch) findViewById(R.id.switchSound);
        sMusic = (Switch) findViewById(R.id.switchMusic);

        mp = MediaPlayer.create(getApplicationContext(), R.raw.knock);

        registerCallbacks();

        initializeWigets();


    }

    private void initializeWigets() {
        if (settings.getBoolean("sound", true)) {
            sSound.setChecked(true);

        } else {
            sSound.setChecked(false);
        }

        if (settings.getBoolean("music", true)) {
            sMusic.setChecked(true);

        } else {
            sMusic.setChecked(false);
        }
    }

    private void registerCallbacks() {
        sSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                playSound();
                if (isChecked) {
                    settings.edit().putBoolean("sound", true).apply();

                } else {
                    settings.edit().putBoolean("sound", false).apply();
                }
            }


        });

        sMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                playSound();
                if (isChecked) {
                    settings.edit().putBoolean("music", true).apply();

                } else {
                    settings.edit().putBoolean("music", false).apply();
                }
            }


        });
    }

    private void playSound() {
        SharedPreferences settings = getSharedPreferences(GM.PREFS_NAME, 0);
//mp = MediaPlayer.create(getApplicationContext(), R.raw.click);
        if (settings.getBoolean("sound", true)) {
            mp.start();
        }
    }

}
