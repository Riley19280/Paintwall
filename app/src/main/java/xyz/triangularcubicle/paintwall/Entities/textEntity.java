package xyz.triangularcubicle.paintwall.Entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import xyz.triangularcubicle.paintwall.GM;

/**
 * Created by Riley on 7/22/2015.
 */
public class textEntity extends Entity {
    public String text;
    private Paint p;
    private int x, y;

    public textEntity(String text, int X, int Y, int color) {
        p = new Paint();
        p.setColor(color);
        x = X;
        y = Y;
        this.text = text;
        p.setTextSize(50f);
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        GM.getEntityManager().addEntity(this);

    }

    public textEntity(String text, int X, int Y) {
        p = new Paint();
        p.setColor(Color.BLACK);
        x = X;
        y = Y;
        this.text = text;
        p.setTextSize(50f);
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        GM.getEntityManager().addEntity(this);

    }

    @Override
    public void Draw(Canvas canvas) {
        canvas.drawText(text, x, y, p);
    }

    @Override
    public void Update(Long deltaTime) {

    }

}
