package vehicle.areas;

import processing.core.PApplet;
import vehicle.objects.Object;
import vehicle.utils.Constants;

public class DesertArea extends Area {
    public DesertArea(float x, float y) {
        super(x, y, Constants.WIDTH, Constants.HEIGHT / 4);
    }

    public Object[] getObjects() {
        return new Object[0];
    }

    public static Area create() {
        return new DesertArea(0, 0);
    }

    public void draw(PApplet app) {
        app.fill(246, 215, 176);
        app.rect(x, y, width, height);
    }
}