package xyz.triangularcubicle.paintwall;

import android.graphics.Point;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import xyz.triangularcubicle.paintwall.Entities.ball;
import xyz.triangularcubicle.paintwall.Entities.playerBall;

/**
 * Created by Riley on 7/15/2015.
 */
public class BallManager {
    private final Random rand = new Random();
    private final Point screenDims = new Point(GM.getScreenDims());
    private final int ball_density = 100;
    private int numOfBallsToStart;


    private List<ball> balls = new ArrayList<>();

    public BallManager() {

    }

    ///////////////////////////AUTO GENERATE ALL BALLS///////////////////////////////////////////////
    public void addStartBalls() {

        points.clear();
        numOfBallsToStart = (int) (((float) screenDims.x / (float) screenDims.y) * (float) ball_density);
        GenerateValidPoints(numOfBallsToStart);

        for (int i = 0; i < numOfBallsToStart; i++) {
            ball b = new ball(getNextValidPoint(), GM.getUsedColor());
            balls.add(b);
            GM.getEntityManager().addEntity(b);
        }

    }

    public void addAnotherBall() {
        int MAX_TRIES = 500;
        int NUM_TRIES = 0;
        Point loc = new Point();


        int start_min_dist_mod = 3;
        int min_dist_mod = start_min_dist_mod;
        int minDist = GM.getBallRadius() * min_dist_mod;

        boolean isValid = false;

        do {
            Point p = getRandomPoint();
            int ctr = 0;
            NUM_TRIES++;

            //end if it cant find a suitable point
            if (NUM_TRIES >= MAX_TRIES) {
                min_dist_mod = 2;
                minDist = GM.getBallRadius() * min_dist_mod;
                NUM_TRIES = 0;
                //Log.i("betterinf", "suitable point not found!!!, min dist:  " + Integer.toString(minDist));
            }

            for (ball q : balls) {
                if (getDist(p, q.center) > minDist) {
                    ctr++;
                } else {
                    break;
                }

            }
            if (ctr == balls.size()) {
                loc = p;
                isValid = true;
            }
        }
        while (isValid == false);


        ball b = new ball(loc, GM.getUsedColor());
        balls.add(b);
        GM.getEntityManager().addEntity(b);
        min_dist_mod = start_min_dist_mod;

    }

    private List<Point> points = new ArrayList<>();

    private int MAX_TRIES = 500;
    private int NUM_TRIES = 0;

    private void GenerateValidPoints(int num) {
        int start_min_dist_mod = 3;
        int min_dist_mod = start_min_dist_mod;

        int minDist = GM.getBallRadius() * min_dist_mod;

        for (int i = 0; i < num; i++) {
            boolean isValid = false;

            do {
                Point p = getRandomPoint();
                int ctr = 0;
                NUM_TRIES++;

                //end if it cant find a suitable point (should never happen, due to density)
                if (NUM_TRIES >= MAX_TRIES) {
                    min_dist_mod--;
                    minDist = GM.getBallRadius() * min_dist_mod;
                    NUM_TRIES = 0;
                    Log.i("betterinf", "suitable point not found!!!");
                }


                if (points.size() > 0)
                    for (Point q : points) {
                        if (getDist(p, q) > minDist) {
                            ctr++;
                        } else {
                            break;
                        }

                    }
                if (ctr == points.size()) {
                    points.add(p);
                    min_dist_mod = start_min_dist_mod;
                    isValid = true;
                }
            }
            while (isValid == false);
        }

    }

    private Point getRandomPoint() {
        final int bufferMod = 2;
        return new Point((GM.getBoarder_width() * bufferMod) + rand.nextInt(screenDims.x - (2 * GM.getBoarder_width() * bufferMod)),
                (GM.getBoarder_width() * bufferMod) + rand.nextInt(screenDims.y - (2 * GM.getBoarder_width() * bufferMod)));

    }

    private Point getNextValidPoint() {
        Point p = points.get(0);
        points.remove(0);
        return p;
    }

    private double getDist(Point a, Point b) {
        return Math.sqrt(((a.x - b.x) * (a.x - b.x)) + ((a.y - b.y) * (a.y - b.y)));
    }

/////////////////////////PLAYER BALL STUFF/////////////////////////

    public void newPlayerBall(Point startLoc, Point velocity) {
        Point p = new Point(startLoc);

        if (p.x < GM.getBoarder_width())
            p.x = GM.getBoarder_width() + (GM.getBallRadius());

        if (p.x > GM.getScreenDims().x - GM.getBoarder_width()) {

            p.x = GM.getScreenDims().x - GM.getBoarder_width() - GM.getBallRadius();
        }
        if (p.y < GM.getBoarder_width())
            p.y = GM.getBoarder_width() + (GM.getBallRadius());

        if (p.y > GM.getScreenDims().y - GM.getBoarder_width())
            p.y = GM.getScreenDims().y - GM.getBoarder_width() - GM.getBallRadius();

        GM.getEntityManager().addEntity(new playerBall(p, velocity));

    }

    public void CLEARALL() {
        points.clear();
        balls.clear();
    }

    public void removeBall(ball b) {
        balls.remove(b);

    }


    public int getBallsOnScreen() {
        return balls.size();
    }
}

