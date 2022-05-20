package vehicle.objects.plants;

import processing.core.PApplet;
import vehicle.Sketch;
import vehicle.objects.Object;

import static vehicle.utils.Constants.CAR_HEIGHT;
import static vehicle.utils.Constants.CAR_WIDTH;

public class Log extends Object {
    public Log(float x, float y) {
        super(x, y);
    }

    public float getWidth() {
        return CAR_WIDTH;
    }

    public float getHeight() {
        return CAR_HEIGHT * 5;
    }

    public void draw(PApplet app) {
        app.image(Sketch.backgroundImages.get("log"), x, y);
    }
}