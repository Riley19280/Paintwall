package xyz.triangularcubicle.paintwall;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class highscoreActivity extends Activity {

    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_highscore);

        SharedPreferences settings = getSharedPreferences(GM.PREFS_NAME, 0);

        TextView yourScore = (TextView) findViewById(R.id.txtYourScore);
        TextView highScore = (TextView) findViewById(R.id.txtHighscore);

        yourScore.setText("Your Score: " + Integer.toString(GM.points));
        highScore.setText("Highscore: " + settings.getInt("highscore", -1));


        if (HighscoreManager.checkHighscore(GM.points, this)) {
            findViewById(R.id.txtNewHighscore).setVisibility(View.VISIBLE);
        }

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.ad_HighscoreInt));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                // beginPlayingGame();
            }
        });

        requestNewInterstitial();


    }

    public void btnMainMenu(View view) {
        this.finish();
        GM.gameActivity.finish();
        ShowAD();
    }

    public void btnAgain(View view) {
        GM.NEWGAME();
        this.finish();
        ShowAD();
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    void ShowAD() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }

    }
}
