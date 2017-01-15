package xyz.triangularcubicle.paintwall;

import android.content.Intent;
import android.os.CountDownTimer;

import xyz.triangularcubicle.paintwall.Entities.Entity;
import xyz.triangularcubicle.paintwall.Entities.playerBall;
import xyz.triangularcubicle.paintwall.Entities.textEntity;


/**
 * Created by Riley on 7/22/2015.
 */
public class WinManager {
    private int secRemaining = 0;
    private textEntity pointTxt;
    private CountDownTimer cd;


    public void Update(Long deltaTime) {
        if (pointTxt != null)
            pointTxt.text = Integer.toString(GM.points);

        if (GM.getBallManager().getBallsOnScreen() <= 0) {
            int playerballs = 0;
            for (Entity e : GM.getEntityManager().entities) {
                if (e.getClass() == playerBall.class) {
                    playerballs++;
                }
            }

            if (playerballs <= 0)
                gameOver();
        }
    }

    public void pointCounter() {
        pointTxt = new textEntity("0000", GM.getScreenDims().x - 150, 50);

    }

    private textEntity timerTxt;

    public void startCountdown(int seconds) {
        timerTxt = new textEntity("", 50, 50);

        if (GM.GameMode != GM.GAMEMODE.ENDLESS) {
            if (cd != null)
                cd.cancel();
            cd = new CountDownTimer(seconds * 1000, 1000) {

                public void onTick(long millisUntilFinished) {
                    secRemaining = (int) millisUntilFinished / 1000;
                    GM.timeLeft = secRemaining;
                    if (secRemaining % 60 > 9)
                        timerTxt.text = ((secRemaining / 60) + ":" + secRemaining % 60);
                    else
                        timerTxt.text = ((secRemaining / 60) + ":0" + secRemaining % 60);
                }

                public void onFinish() {
                    //TODO: game over here

                    timerTxt.text = ("0:00");
                    gameOver();

                }


            }.start();

        }
    }

    public void gameOver() {
        if (cd != null) {
            cd.cancel();
        }
        if (!GM.GAMEOVER) {
            Intent i = new Intent(GM.gameActivity.getApplicationContext(), highscoreActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            GM.gameActivity.getApplicationContext().startActivity(i);
            GM.GAMEOVER = true;
        }
    }

    boolean paused = false;

    public void pauseTimer() {
        paused = true;
        if (cd != null)
            cd.cancel();

    }

    public void resumeTimer() {
        if (paused) {
            timerTxt.destroy();
            startCountdown(GM.timeLeft);
            paused = false;
        }
    }

}
