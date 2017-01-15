package xyz.triangularcubicle.paintwall.Entities;

import android.graphics.Canvas;
import android.graphics.Point;

import xyz.triangularcubicle.paintwall.Base;
import xyz.triangularcubicle.paintwall.GM;

/**
 * Created by Riley on 7/12/2015.
 */
public abstract class Entity extends Base {
    public Point center;

    public Entity() {
        center = new Point();
    }


    abstract public void Draw(Canvas canvas);


    abstract public void Update(Long deltaTime);

    public void destroy(){
        GM.getEntityManager().delEntity(this);
    }


}
