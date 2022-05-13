package vehicle.objects.vehicles;

import processing.core.PApplet;
import vehicle.objects.Vehicle;

import static vehicle.utils.Constants.CAR_HEIGHT;
import static vehicle.utils.Constants.CAR_WIDTH;

public class Car extends Vehicle {
    public Car(float x, float y){
        super(x, y);
    }

    public float getWidth(){
        return CAR_WIDTH;
    }

    public float getHeight(){
        return CAR_HEIGHT;
    }

    public void draw(PApplet app){
        app.rect(x, y, getWidth(), getHeight() - 10);
        app.ellipse(x + 5, y + getHeight() - 5, 10, 10);
        app.ellipse(x + getWidth() - 5, y + getHeight() - 5, 10, 10);
    }
}
