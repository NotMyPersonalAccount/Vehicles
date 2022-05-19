package vehicle.areas;

import processing.core.PApplet;
import vehicle.Sketch;
import vehicle.utils.Constants;
import vehicle.views.GameView;

import java.util.ArrayList;

public class Spawn extends Area {
    public Spawn(float x, float y) {
        super(x, y, Constants.CANVAS_WIDTH, Constants.CAR_HEIGHT * 8);
    }

    public static Area create() {
        return new Spawn(0, 0);
    }

    public void tick(GameView game) {

    }

    public void draw(PApplet app) {
        for(float grassX = x; grassX < x + width; grassX += Constants.CAR_WIDTH) {
            for(float grassY = y; grassY < y + height; grassY += Constants.CAR_HEIGHT) {
                app.image(Sketch.backgroundImages.get("grass"), grassX, grassY, Constants.CAR_WIDTH, Constants.CAR_HEIGHT);
            }
        }
    }

    public boolean checkSpawnConditions(ArrayList<Area> areas) {
        return areas.size() == 0;
    }
}
