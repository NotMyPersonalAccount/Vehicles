package vehicle.objects.vehicles;

import processing.core.PApplet;
import vehicle.Sketch;
import vehicle.objects.Vehicle;

import static vehicle.utils.Constants.CAR_HEIGHT;
import static vehicle.utils.Constants.CAR_WIDTH;

public class Car extends Vehicle {
    private final String color;

    public Car(String color, float x, float y){
        super(x, y);
        this.color = color;
    }

    public float getWidth(){
        return CAR_WIDTH;
    }

    public float getHeight(){
        return CAR_HEIGHT;
    }

    public void draw(PApplet app){
        app.image(Sketch.carImages.get(color), x, y);
    }
}
