package vehicle.areas;

import processing.core.PApplet;
import vehicle.objects.Object;
import vehicle.utils.Constants;
import vehicle.views.GameView;

public class GrassArea extends Area {
    public GrassArea(float x, float y) {
        super(x, y, Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT / 2);
    }

    public Object[] getObjects() {
        return new Object[0];
    }

    public static Area create() {
        return new GrassArea(0, 0);
    }

    public void tick(GameView game) {

    }

    public void draw(PApplet app) {
        app.fill(0, 255, 0);
        app.rect(x, y, width, height);
    }
}
