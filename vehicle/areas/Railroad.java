package vehicle.areas;

import processing.core.PApplet;
import vehicle.views.GameView;

import java.util.ArrayList;

import static vehicle.utils.Constants.CANVAS_WIDTH;
import static vehicle.utils.Constants.CAR_HEIGHT;

public class Railroad extends Area {

    public Railroad(float x, float y) {
        super(x, y, CANVAS_WIDTH, CAR_HEIGHT);
    }

    public static Area create() {
        return new Railroad(0, 0);
    }

    public void tick(GameView game) {

    }

    public void draw(PApplet app) {
        app.fill(138, 135, 128);
        app.rect(x, y, width, height);
    }

    public boolean checkSpawnConditions(ArrayList<Area> areas) {
        return super.checkSpawnConditions(areas) && !(areas.get(areas.size() - 1) instanceof River);
    }
}
