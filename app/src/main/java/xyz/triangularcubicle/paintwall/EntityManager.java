package xyz.triangularcubicle.paintwall;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

import xyz.triangularcubicle.paintwall.Entities.Entity;

/**
 * Created by Riley on 7/12/2015.
 */
public class EntityManager extends Base {
    public volatile boolean updating;

    final private List<Entity> add_entities;
    final private List<Entity> del_entities;
    final public List<Entity> entities;

    public EntityManager() {
        add_entities = new ArrayList<>();
        del_entities = new ArrayList<>();
        entities = new ArrayList<>(250);
        updating = false;

    }


    @Override
    public void Update(Long deltaTime) {

        updating = true;
        synchronized (entities) {
            for (Entity e : entities) {
                e.Update(deltaTime);
            }
        }
        updating = false;

        for (Entity e : add_entities) {
            entities.add(e);
        }

        for (Entity e : del_entities) {
            entities.remove(e);
        }

        add_entities.clear();
        del_entities.clear();


    }

    @Override
    public void Draw(Canvas canvas) {
        updating = true;
        synchronized (entities) {
            for (Entity e : entities) {
                e.Draw(canvas);
            }
        }

        updating = false;


    }

    public void addEntity(Entity e) {
        if (!updating) {
            updating = true;
            entities.add(e);
            updating = false;
        } else

            add_entities.add(e);
    }

    public void delEntity(Entity e) {
        if (!updating) {
            updating = true;
            entities.remove(e);
            updating = false;
        } else
            del_entities.add(e);

    }

    public void CLEARALL() {
        add_entities.clear();
        del_entities.clear();
        entities.clear();
    }

    public int getNumOfEntities(){return  entities.size();}
}
