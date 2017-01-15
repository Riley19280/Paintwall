package xyz.triangularcubicle.paintwall.Entities;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import xyz.triangularcubicle.paintwall.GM;

/**
 * Created by Riley on 7/14/2015.
 */
public class ball extends Entity {

    private int radius;
    private int color;
    private Paint p;

    public ball(Point center, int color) {
        this.center = center;
        radius = GM.getBallRadius();
        this.color = color;

        p = new Paint();
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        p.setColor(color);

    }

    @Override
    public void Update(Long deltaTime) {

    }

    @Override
    public void Draw(Canvas canvas) {
        canvas.drawCircle(center.x, center.y, radius, p);
    }

    public int getColor() {
        return color;
    }

    @Override
    public void destroy() {
        if (GM.GameMode == GM.GAMEMODE.ENDLESS) {
            GM.getBallManager().addAnotherBall();

        }

        GM.getBallManager().removeBall(this);
        super.destroy();
    }
}
