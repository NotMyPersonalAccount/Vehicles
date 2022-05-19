package vehicle.objects.vehicles;

import processing.core.PApplet;
import vehicle.Sketch;
import vehicle.objects.Vehicle;

import static vehicle.utils.Constants.CAR_HEIGHT;
import static vehicle.utils.Constants.CAR_WIDTH;

public class Tank extends Vehicle {
    public Tank(float x, float y){
        super(x, y);
    }

    public float getWidth(){
        return CAR_WIDTH * 2;
    }

    public float getHeight(){
        return CAR_HEIGHT;
    }

    public void draw(PApplet app){
        app.image(Sketch.vehicleImages.get("tank"), x, y, CAR_WIDTH * 2, CAR_HEIGHT);
    }
}
