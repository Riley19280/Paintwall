package xyz.triangularcubicle.paintwall.Entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

import xyz.triangularcubicle.paintwall.GM;

/**
 * Created by Riley on 7/14/2015.
 */
public class wall extends Entity {
    final Rect rect;
    int value;
    java.lang.Integer color;
    Paint p;
    int index = 0;

    boolean moving = false;

    public wall(Rect dims, int value) {
        this.rect = dims;
        this.value = value;

        switch (value) {
            case 0:
                color = Color.LTGRAY;
                break;
            case 1:
                color = GM.getNextWallColor();
                break;
        }
        init();
    }

    public wall(Rect dims, int value, int index) {
        this.rect = dims;
        this.value = value;
        this.index = index;
        moving = true;

        if (moving) {
            switch (value) {
                case 0:
                    color = Color.LTGRAY;
                    break;
                case 1:
                    color = GM.getNextWallColor();
                    color = GM.getUsedColors().get(index);
                    break;
            }
        }
        init();
    }

    void init() {
        if (GM.GameMode == GM.GAMEMODE.NORMAL)
            changeInterval = 1000000000 * 2;
        else if (GM.GameMode == GM.GAMEMODE.HARDCORE)
            changeInterval = 1000000000;

        center.x = rect.centerX();
        center.y = rect.centerY();

        p = new Paint();
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        p.setColor(color);
    }

    private long changeInterval = 1000000000;

    private long futureTime = System.nanoTime() + changeInterval;

    @Override
    public void Update(Long deltaTime) {
        Random rand = new Random();

        if (color != Color.LTGRAY)
            if ((System.nanoTime() >= futureTime) && moving) {

                index++;
                // GM.getGenBoardGfx().colored_walls.indexOf(this);

                int newColor = GM.getUsedColors().get(index % GM.getGenBoardGfx().colored_walls.size());
                this.color = newColor;
                p.setColor(newColor);
                futureTime += changeInterval;


            }

    }

    @Override
    public void Draw(Canvas canvas) {
        canvas.drawRect(rect, p);
    }
}
