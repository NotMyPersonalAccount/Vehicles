package vehicle;

import processing.core.PApplet;
import vehicle.areas.Area;
import vehicle.objects.Vehicle;
import vehicle.objects.vehicles.Car;

public class Sketch extends PApplet {
    private Area[] areas = {};
    private Vehicle playerVehicle = new Car(50, 50);

    public void settings() {
        size(800, 800);
    }

    public void draw(){
        background(255);

        for (Area a : areas) {
            a.draw(this);
        }
        playerVehicle.draw(this);
    }
}