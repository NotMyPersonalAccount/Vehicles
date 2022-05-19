package vehicle.objects.vehicles;

import processing.core.PApplet;
import vehicle.Sketch;
import vehicle.objects.Vehicle;

import static vehicle.utils.Constants.CAR_HEIGHT;
import static vehicle.utils.Constants.CAR_WIDTH;

public class Train extends Vehicle {
    public Train(float x, float y) {
        super(x, y);
    }

    public float getWidth() {
        return CAR_WIDTH * 10;
    }

    public float getHeight() {
        return CAR_HEIGHT;
    }

    public void draw(PApplet app) {
        app.image(Sketch.vehicleImages.get("train"), x, y, CAR_WIDTH * 10, CAR_HEIGHT);
    }
}
