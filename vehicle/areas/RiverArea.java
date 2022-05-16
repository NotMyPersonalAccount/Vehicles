package vehicle.areas;

import processing.core.PApplet;
import vehicle.objects.Object;
import vehicle.utils.Constants;

public class RiverArea extends Area {
    public RiverArea(float x, float y) {
        super(x, y, Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT / 4);
    }

    public Object[] getObjects() {
        return new Object[0];
    }

    public boolean isUnsafeSpawn() {
        return false;
    }

    public static Area create() {
        return new RiverArea(0, 0);
    }

    public void draw(PApplet app) {
        app.fill(0, 0, 255);
        app.rect(x, y, width, height);
    }
}
