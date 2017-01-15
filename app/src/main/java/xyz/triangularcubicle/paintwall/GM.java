package xyz.triangularcubicle.paintwall;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Riley on 7/12/2015.
 */
public class GM {
    //static variables
    private static Random rand = new Random();
    public static final String PREFS_NAME = "paintwallprefs";

    public static GAMEMODE GameMode = GAMEMODE.NORMAL;

    public enum GAMEMODE {
       EASY, NORMAL, ENDLESS, HARDCORE, FLAWLESS
    }

    public static int timeLeft;
    public static int points = 0;
    private static int ballRadius = 25;
    private static int boarder_width;

    private static BallManager ballManager;
    private static EntityManager entityManager;
    //public static boolean shouldFinishGameActivity= false;

    private static WinManager winManager;
    private static GenerateBoarderGraphics genBoardGfx;


    private static Point screenDims = new Point();
    public static Activity gameActivity;

    public static boolean GAMEOVER = false;

    public GM() {
        entityManager = new EntityManager();
        genBoardGfx = new GenerateBoarderGraphics();
        ballManager = new BallManager();
        winManager = new WinManager();

        ballRadius = getScreenDims().x / 43;

    }

    private static List<Integer> availColors = new ArrayList<>();
    public static List<Integer> usedColors = new ArrayList<>();

    private static void genColors() {
        availColors.add(Colors.RED);
        availColors.add(Colors.GREEN);
        availColors.add(Colors.DKGREEN);
        availColors.add(Colors.BLUE);
        availColors.add(Colors.LTBLUE);
        availColors.add(Colors.AQUA);
        availColors.add(Colors.ORANGE);
        availColors.add(Colors.PURPLE);
        availColors.add(Colors.YELLOW);
        availColors.add(Colors.PINK);
        availColors.add(Colors.BROWN);
    }

    public static int getNextWallColor() {
        int i = rand.nextInt(availColors.size());
        int c = availColors.get(i);
        usedColors.add(c);
        availColors.remove(i);

        return c;
    }

    public static int getUsedColor() {
        return usedColors.get(rand.nextInt(usedColors.size()));
    }

    public static List<java.lang.Integer> getUsedColors() {
        return usedColors;
    }


    public static void NEWGAME() {
        entityManager.CLEARALL();
        ballManager.CLEARALL();
        availColors.clear();
        usedColors.clear();
        points = 0;
        genColors();
getGenBoardGfx().GenerateBoarder();
        ballManager.addStartBalls();
        GAMEOVER = false;
        winManager.startCountdown(180);
        winManager.pointCounter();

    }

    public static class Colors {
        public static int RED = Color.rgb(255, 0, 0);
        public static int GREEN = Color.rgb(0, 255, 0);
        public static int BLUE = Color.rgb(0, 0, 255);
        public static int LTBLUE = Color.rgb(137, 192, 255);
        public static int YELLOW = Color.rgb(255, 255, 0);
        public static int AQUA = Color.rgb(0, 255, 255);
        public static int PURPLE = Color.rgb(102, 0, 204);
        public static int ORANGE = Color.rgb(255, 128, 0);
        public static int WHITE = Color.rgb(255, 255, 255);
        public static int BLACK = Color.rgb(0, 0, 0);
        public static int LTLTGRAY = Color.rgb(223, 223, 223);
        public static int DKGREEN = Color.rgb(0, 102, 0);
        public static int PINK = Color.rgb(250, 100, 185);
        public static int BROWN = Color.rgb(102, 51, 0);
    }

    //gets and sets
    public static EntityManager getEntityManager() {
        return entityManager;
    }

    public static Point getScreenDims() {
        return screenDims;
    }

    public static void setScreenDims(Point screenDims) {
        GM.screenDims = screenDims;
    }

    public static int getBallRadius() {
        return ballRadius;
    }

    public static int getBoarder_width() {
        return boarder_width;
    }

    public static void setBoarder_width(int boarder_width) {

        GM.boarder_width = boarder_width;
    }

    public static BallManager getBallManager() {
        return ballManager;
    }

    public static WinManager getWinManager() {
        return winManager;
    }

    public static GenerateBoarderGraphics getGenBoardGfx() {
        return genBoardGfx;
    }

}
