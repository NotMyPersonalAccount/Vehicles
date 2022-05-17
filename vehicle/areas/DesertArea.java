package vehicle.areas;

import processing.core.PApplet;
import vehicle.objects.Object;
import vehicle.utils.Constants;
import vehicle.views.GameView;

public class DesertArea extends Area {
    public DesertArea(float x, float y) {
        super(x, y, Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT / 4);
    }

    public Object[] getObjects() {
        return new Object[0];
    }

    public static Area create() {
        return new DesertArea(0, 0);
    }

    public void tick(GameView game){

    }

    public void draw(PApplet app) {
        app.fill(246, 215, 176);
        app.rect(x, y, width, height);
    }
}
