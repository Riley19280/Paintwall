package xyz.triangularcubicle.paintwall;

import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

import xyz.triangularcubicle.paintwall.Entities.wall;

/**
 * Created by Riley on 7/14/2015.
 */
public class GenerateBoarderGraphics {
    private Point screenDims;
    private int boarder_width;
    private int segments_width;
    private int segments_height;
    private int segments_width_length;
    private int segments_height_length;
    public List<wall> walls = new ArrayList<>();
    public List<wall> colored_walls = new ArrayList<>();

    int index = 0;

    public GenerateBoarderGraphics() {

    }

    private void doSetup() {
        screenDims = new Point(GM.getScreenDims());
        boarder_width = (int) (GM.getScreenDims().x * .08f);
        GM.setBoarder_width(boarder_width);

        segments_height = 7;
        segments_width = 3;

        segments_width_length = screenDims.x / segments_width;

        segments_height_length = screenDims.y / segments_height;

        walls.clear();
        colored_walls.clear();
        index = 0;
    }

    public void GenerateBoarder(){
        if (GM.GameMode == GM.GAMEMODE.EASY)
            GenerateStaticBoarder();
        else
            GenetateDynamicBorder();
    }

    private void GenerateStaticBoarder() {
        doSetup();

        for (int x = 0; x < segments_width; x++) {
            //draws top
            Rect r = new Rect(x * segments_width_length, 0, segments_width_length * (x + 1), boarder_width);
            wall w = new wall(r, x % 2);
            walls.add(w);
            if (x % 2 == 1) {
                colored_walls.add(w);
            }
            GM.getEntityManager().addEntity(w);

            //draws bottom
            Rect t = new Rect(x * segments_width_length, screenDims.y - boarder_width, segments_width_length * (x + 1), screenDims.y);
            wall w1 = new wall(t, x % 2);
            walls.add(w1);
            if (x % 2 == 1) {
                colored_walls.add(w1);
            }
            GM.getEntityManager().addEntity(w1);
        }
        for (int y = 0; y < segments_height; y++) {
            //left
            Rect r = new Rect(0, y * segments_height_length, boarder_width, segments_height_length * (y + 1));
            wall w = new wall(r, y % 2);
            walls.add(w);
            if (y % 2 == 1) {
                colored_walls.add(w);
            }
            GM.getEntityManager().addEntity(w);

            //right
            Rect t = new Rect(screenDims.x - boarder_width, y * segments_height_length, screenDims.x, segments_height_length * (y + 1));
            wall w1 = new wall(t, y % 2);
            walls.add(w1);
            if (y % 2 == 1) {
                colored_walls.add(w1);
            }
            GM.getEntityManager().addEntity(w1);
        }
    }

     private void GenetateDynamicBorder() {
        doSetup();
        for (int x = 0; x < segments_width; x++) {
            //draws bottom
            Rect t = new Rect(x * segments_width_length, screenDims.y - boarder_width, segments_width_length * (x + 1), screenDims.y);
            wall w1;

            if (x % 2 == 1) {
                w1 = new wall(t, x % 2, index);
                colored_walls.add(w1);
                index++;
            } else {
                w1 = new wall(t, x % 2);
                walls.add(w1);
            }
            GM.getEntityManager().addEntity(w1);
        }


        for (int y = segments_height-1; y >= 0; y--) {
            //left
            Rect r = new Rect(0, y * segments_height_length, boarder_width, segments_height_length * (y + 1));
            wall w1;

            if (y % 2 == 1) {
                w1 = new wall(r, y % 2, index);
                colored_walls.add(w1);
                index++;
            } else {
                w1 = new wall(r, y % 2);
                walls.add(w1);
            }
            GM.getEntityManager().addEntity(w1);
        }


        for (int x = 0; x < segments_width; x++) {
            //draws top
            Rect r = new Rect(x * segments_width_length, 0, segments_width_length * (x + 1), boarder_width);
            wall w1;

            if (x % 2 == 1) {
                w1 = new wall(r, x % 2, index);
                colored_walls.add(w1);
                index++;
            } else {
                w1 = new wall(r, x % 2);
                walls.add(w1);
            }
            GM.getEntityManager().addEntity(w1);
            ;

        }


        for (int y = 0; y < segments_height; y++) {
            //right
            Rect t = new Rect(screenDims.x - boarder_width, y * segments_height_length, screenDims.x, segments_height_length * (y + 1));
            wall w1;

            if (y % 2 == 1) {
                w1 = new wall(t, y % 2, index);
                colored_walls.add(w1);
                index++;
            } else {
                w1 = new wall(t, y % 2);
                walls.add(w1);
            }
            GM.getEntityManager().addEntity(w1);
        }
    }

}
