package xyz.triangularcubicle.paintwall;

import android.content.Context;

/**
 * Created by Riley on 7/11/2015.
 */
public class GameRoot implements Runnable {
    //renderer
    private final CanvasRenderer canvasRenderer;
    final GM gm;


    volatile boolean running;
    final int targetFPS;
    long targetStepPeriod;
    Thread thread;
    public long deltaTime;

    public GameRoot(Context context) {
        gm = new GM();

        running = true;
        targetFPS = 60;
        targetStepPeriod = 1000000000 / targetFPS;
        canvasRenderer = new CanvasRenderer(context);

        GM.NEWGAME();
        deltaTime = 0;

    }


    @Override
    public void run() {
        long startRun;
        long startStep, endStep;
        long sleepTime, overSleepTime;
        long timeBeyondPeriod = 0;
        startRun = System.nanoTime() - targetStepPeriod;
        startStep = startRun;
        overSleepTime = 0L;
        try {
            while (running) {
                long currTime = System.nanoTime();
                startStep = currTime;

                Update(deltaTime);

                endStep = System.nanoTime();

                deltaTime = (startStep - endStep);
                sleepTime = (targetStepPeriod - (endStep - startStep)) - overSleepTime;

                if (sleepTime > 0) {
                    Thread.sleep(sleepTime / 1000000L);

                    overSleepTime = (System.nanoTime() - endStep) - sleepTime;
                } else {
                    timeBeyondPeriod = timeBeyondPeriod - sleepTime;
                    overSleepTime = 0L;
                    while (timeBeyondPeriod > targetStepPeriod) {
                        Update(deltaTime);
                        timeBeyondPeriod -= targetStepPeriod;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void Update(Long deltaTime) {
        synchronized (GM.getEntityManager().entities) {
            GM.getEntityManager().Update(deltaTime);
        }
        GM.getWinManager().Update(deltaTime);
        TouchHandler.Update(deltaTime);

        canvasRenderer.Update();
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
