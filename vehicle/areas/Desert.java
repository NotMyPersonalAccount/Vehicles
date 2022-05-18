package vehicle.areas;

import processing.core.PApplet;
import vehicle.utils.Constants;
import vehicle.views.GameView;

public class Desert extends Area {
    public Desert(float x, float y) {
        super(x, y, Constants.CANVAS_WIDTH, Constants.CAR_HEIGHT * 8);
    }

    public static Area create() {
        return new Desert(0, 0);
    }

    public void tick(GameView game) {

    }

    public void draw(PApplet app) {
        app.fill(247, 224, 169);
        app.rect(x, y, width, height);
    }
}
