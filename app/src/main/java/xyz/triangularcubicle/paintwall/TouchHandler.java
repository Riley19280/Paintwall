package xyz.triangularcubicle.paintwall;


import android.graphics.Point;
import android.view.MotionEvent;

/**
 * Created by Riley on 7/15/2015.
 */
public class TouchHandler {
    public TouchHandler() {

    }

    static Point down;
    static Point up;
    static boolean isUp = false;
    static boolean isDown = false;

    public static void processEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                try {
                    down = new Point();
                    down.x = (int) event.getX();
                    down.y = (int) event.getY();
                } catch (Exception e) {
                }
                isDown = true;

                break;

            case MotionEvent.ACTION_UP:
                try {
                    up = new Point();
                    up.x = (int) event.getX();
                    up.y = (int) event.getY();
                } catch (Exception e) {
                }
                isUp = true;

                break;

            case MotionEvent.ACTION_MOVE:

                break;
        }
    }

    public static void Update(Long deltaTime) {
        if (isDown && isUp) {
            //event has happened
            isDown = false;
            isUp = false;


            Point vel = new Point();
            vel.x = down.x - up.x;
            vel.y = down.y - up.y;
            GM.getBallManager().newPlayerBall(down, vel);
            down = null;
            up = null;
        }

    }

}



