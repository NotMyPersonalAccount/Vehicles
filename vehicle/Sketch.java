package vehicle;

import processing.core.PApplet;
import vehicle.areas.Area;
import vehicle.areas.GrassArea;
import vehicle.areas.RiverArea;
import vehicle.objects.Vehicle;
import vehicle.objects.vehicles.Car;
import vehicle.utils.AreaCreator;
import vehicle.utils.Constants;

import java.util.ArrayList;

public class Sketch extends PApplet {
    private ArrayList<Area> areas = new ArrayList<>();
    private Vehicle playerVehicle = new Car(50, 50);

    public void settings() {
        size(800, 800);
    }

    public void draw() {
        background(255);
        createMap();

        for (int i = 0; i < areas.size(); i++) {
            Area a = areas.get(i);
            if (a.y >= Constants.HEIGHT) {
                areas.remove(i);
                break;
            }

            a.move(0, 1);
            pushMatrix();
            a.draw(this);
            popMatrix();
        }
        playerVehicle.draw(this);
    }

    public Area createArea(float x, float y) {
        AreaCreator[] callable = {GrassArea::create, RiverArea::create};
        return callable[(int) random(callable.length)].call(x, y);
    }

    private void createMap() {
        float y;
        if (areas.size() == 0) {
            y = Constants.HEIGHT;
        } else {
            Area topArea = areas.get(areas.size() - 1);
            y = topArea.y - topArea.height;
        }
        while (y >= -Constants.HEIGHT) {
            Area a = createArea(0, y);
            areas.add(a);
            y -= a.height;
        }
    }
}