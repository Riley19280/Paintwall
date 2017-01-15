package xyz.triangularcubicle.paintwall;


import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Riley on 7/11/2015.
 */

public class CanvasRenderer extends View {

    Thread thread = null;


    volatile boolean running = false;
    volatile boolean drawNeeded = false;

    private int x;
    private int y;

    //Constructors
    public CanvasRenderer(Context context) {
        super(context);
        doSetup(context);
    }

    public CanvasRenderer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasRenderer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void doSetup(Context context) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //preform drawing here!!!!!

            canvas.drawColor(GM.Colors.LTLTGRAY);
            synchronized (GM.getEntityManager().entities) {
                doDraw(canvas);

            }
            //draw has finished, another one is now needed
            //done in doDraw


            ;
            //PPP  invalidate();
            invalidate();

    }

    private void doDraw(Canvas canvas) {
        GM.getEntityManager().Draw(canvas);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        TouchHandler.processEvent(event);
        return true;
    }


    public void Update() {
            if (drawNeeded) {
                //hands off to UI thread for hardware acc.
                drawNeeded = false;
            }
            try {
                Thread.sleep(10);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Log.i("betterinf","main control " + Boolean.toString(drawNeeded));

    }




    }






