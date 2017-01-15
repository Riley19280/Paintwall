package xyz.triangularcubicle.paintwall.Entities;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import xyz.triangularcubicle.paintwall.CollisionManager;
import xyz.triangularcubicle.paintwall.GM;

/**
 * Created by Riley on 7/14/2015.
 */
public class playerBall extends Entity {
    int destructTimer = 1000;
    int color;
    Point vel;
    final Paint p;
    int worth = 2;

    public playerBall(Point center, Point velocity) {
        this.center = center;
        this.vel = velocity;
        color = GM.Colors.WHITE;

        p = new Paint();
        p.setStyle(Paint.Style.FILL_AND_STROKE);
        p.setColor(color);
    }

    @Override
    public void Update(Long deltaTime) {
        destructTimer--;
        if (center.x < 0 || center.y < 0 || center.x > GM.getScreenDims().x || center.y > GM.getScreenDims().y || destructTimer <= 0)
            GM.getEntityManager().delEntity(this);

        center.x -= vel.x / 60;
        center.y -= vel.y / 60;

        checkColliding();

    }

    void checkColliding() {
        //get a rectangle surrounding the ball
        Rect rect = new Rect(center.x - GM.getBallRadius(), center.y - GM.getBallRadius(), center.x + GM.getBallRadius(), center.y + GM.getBallRadius());

        synchronized (GM.getEntityManager().entities) {
            //check ball collision
            for (Entity e : GM.getEntityManager().entities) {
                if (e.getClass() == ball.class) {
                    ball b = ((ball) e);

                    if (CollisionManager.DetectCollision(this, b)) {
                        setColor(b.getColor());
                        b.destroy();

                    }
                }

                //check walls
                if (e.getClass() == wall.class) {
                    wall w = ((wall) e);
                    if (Rect.intersects(rect, w.rect)) {

                        if (w.color == Color.LTGRAY) {
                            changeVelocity(w.rect);
                        } else if (w.color == this.color) {
                            GM.points+=worth;
                            this.destroy();
                        } else {
                            if(GM.GameMode  == GM.GAMEMODE.FLAWLESS)
                            {
                                GM.getWinManager().gameOver();
                            }
                            GM.points--;
                            this.destroy();
                        }

                    }
                }

            }


        }


    }

    private void changeVelocity(Rect rect) {
        if (rect.width() > rect.height()) {
            this.vel.y *= -1;
        }

        if (rect.height() > rect.width()) {
            this.vel.x *= -1;
        }
        worth +=1;
    }


    public void setColor(int color) {
        this.color = color;
        p.setColor(color);
    }

    @Override
    public void Draw(Canvas canvas) {
        canvas.drawCircle(center.x, center.y, GM.getBallRadius(), p);
    }
}
