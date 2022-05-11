package vehicle;

import processing.core.PApplet;
import vehicle.areas.Area;
import vehicle.areas.DesertArea;
import vehicle.areas.GrassArea;
import vehicle.areas.RiverArea;
import vehicle.objects.Vehicle;
import vehicle.objects.vehicles.Car;
import vehicle.utils.AreaCreator;
import vehicle.utils.Constants;

import java.util.ArrayList;
import java.util.Iterator;

public class Sketch extends PApplet {
    private ArrayList<Area> areas = new ArrayList<>();
    private Vehicle playerVehicle = new Car(Constants.WIDTH / 2 - 50, Constants.HEIGHT - 35);

    public void settings() {
        size(800, 800);
    }

    public void draw() {
        background(255);
        createMap();

        Iterator<Area> it = areas.iterator();
        while (it.hasNext()) {
            Area a = it.next();
            if (a.y >= Constants.HEIGHT) {
                it.remove();
                continue;
            }

            a.move(0, 1);
            a.draw(this);
        }

        playerVehicle.move(0, 1);
        playerVehicle.draw(this);
    }

    public void keyPressed() {
        if (key == CODED) {
            if (keyCode == UP) {
                playerVehicle.move(0, -5);
            } else if (keyCode == DOWN) {
                playerVehicle.move(0, 5);
            } else if (keyCode == LEFT) {
                playerVehicle.move(-5, 0);
            } else if (keyCode == RIGHT) {
                playerVehicle.move(5, 0);
            }
        }
    }

    public Area createArea() {
        AreaCreator[] callable = {GrassArea::create, RiverArea::create, DesertArea::create};
        Area a = null;
        while (a == null || (areas.size() > 0 && a.getClass() == areas.get(areas.size() - 1).getClass())) {
            a = callable[(int) random(callable.length)].call();
        }
        return a;
    }

    private void createMap() {
        float y;
        if (areas.size() == 0) {
            y = Constants.HEIGHT;
        } else {
            Area topArea = areas.get(areas.size() - 1);
            y = topArea.y;
        }

        while (true) {
            Area a = createArea();
            y -= a.height;
            if (y < -a.height) {
                break;
            }
            a.y = y;
            areas.add(a);
        }
    }
}