package vehicle.objects.vehicles;

import processing.core.PApplet;
import vehicle.objects.Vehicle;

public class Car extends Vehicle {
    public Car(float x, float y){
        super(x, y);
    }

    public float getWidth(){
        return 50;
    }

    public float getHeight(){
        return 25 + 10;
    }

    public void draw(PApplet app){
        app.rect(x, y, getWidth(), getHeight() - 10);
        app.ellipse(x + 5, y + getHeight() - 5, 10, 10);
        app.ellipse(x + getWidth() - 5, y + getHeight() - 5, 10, 10);
    }
}
