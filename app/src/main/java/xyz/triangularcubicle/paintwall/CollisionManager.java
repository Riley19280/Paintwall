package xyz.triangularcubicle.paintwall;

import xyz.triangularcubicle.paintwall.Entities.Entity;

/**
 * Created by Riley on 7/15/2015.
 * this class was going to handle all collisions but for this game it would be less efficient
 */
public class CollisionManager implements Runnable {
    Thread thread = null;


    volatile boolean running = false;

    @Override
    public void run() {
        while (running) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

   public static boolean DetectCollision(Entity e1, Entity e2) {
        float x1 = e1.center.x;
        float y1 = e1.center.y;

        float x2 = e2.center.x;
        float y2 = e2.center.y;

        float r1 = GM.getBallRadius();
        float r2 = GM.getBallRadius();

        if ((float) Math.pow((x2 - x1), 2) + (float) Math.pow((y1 - y2), 2) <= Math.pow((r1 + r2), 2)) {
            return true;
        } else {
            return false;
        }
    }

    public void pause() {
        running = false;
        while (true) {
            try {
                thread.join();
                return;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }


    public void resume() {

        running = true;

        thread = new Thread(this);
        thread.start();
    }
}
