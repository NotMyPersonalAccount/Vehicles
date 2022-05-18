package vehicle.objects.plants;

import processing.core.PApplet;
import vehicle.objects.Object;

import static vehicle.utils.Constants.CAR_HEIGHT;
import static vehicle.utils.Constants.CAR_WIDTH;

public class Cacti extends Object {
    public Cacti(float x, float y) {
        super(x, y);
    }

    public float getWidth() {
        return CAR_WIDTH;
    }

    public float getHeight() {
        return CAR_HEIGHT;
    }

    public void draw(PApplet app) {
        app.fill(16, 91, 8);
        app.rect(x, y, CAR_WIDTH, CAR_HEIGHT);
        app.fill(0);
        app.rect(x + 5, y, 1, CAR_HEIGHT);
        app.rect(x + 12, y, 1, CAR_HEIGHT);
        app.rect(x + 19, y, 1, CAR_HEIGHT);
        app.rect(x + 25, y, 1, CAR_HEIGHT);
        app.rect(x + 32, y, 1, CAR_HEIGHT);
    }
}