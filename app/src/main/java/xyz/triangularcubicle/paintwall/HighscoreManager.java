package xyz.triangularcubicle.paintwall;

import android.app.Activity;
import android.content.SharedPreferences;


/**
 * Created by riley on 6/12/2016.
 */
public class HighscoreManager {
    private static SharedPreferences settings;


    public static boolean checkHighscore(int score, Activity activity) {
        settings = activity.getApplicationContext().getSharedPreferences(GM.PREFS_NAME, 0);
        switch (GM.GameMode) {
            case EASY:
                if (GM.points > settings.getInt("highscore_easy", 0)) {
                    settings.edit().putInt("highscore_easy", GM.points).apply();
                    return true;
                }
                return false;
            case NORMAL:
                if (GM.points > settings.getInt("highscore_normal", 0)) {
                    settings.edit().putInt("highscore_normal", GM.points).apply();
                    return true;
                }
                return false;
            case ENDLESS:
                if (GM.points > settings.getInt("highscore_endless", 0)) {
                    settings.edit().putInt("highscore_endless", GM.points).apply();
                    return true;
                }
                return false;
            case HARDCORE:
                if (GM.points > settings.getInt("highscore_hardcore", 0)) {
                    settings.edit().putInt("highscore_hardcore", GM.points).apply();
                    return true;
                }
                break;
            case FLAWLESS:
                if (GM.points > settings.getInt("highscore_flawless", 0)) {
                    settings.edit().putInt("highscore_flawless", GM.points).apply();
                    return true;
                }
                return false;
        }
        return false;
    }

    public static int getHighscore(GM.GAMEMODE gamemode, Activity activity) {
        settings = activity.getApplicationContext().getSharedPreferences(GM.PREFS_NAME, 0);
        switch (gamemode) {
            case EASY:
                return settings.getInt("highscore_easy", 0);

            case NORMAL:
                return settings.getInt("highscore_normal", 0);

            case ENDLESS:
                return settings.getInt("highscore_endless", 0);

            case HARDCORE:
                return settings.getInt("highscore_hardcore", 0);

            case FLAWLESS:
                return settings.getInt("highscore_flawless", 0);

        }
        return 0;

    }

}
