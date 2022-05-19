package vehicle.areas;

import processing.core.PApplet;
import vehicle.Sketch;
import vehicle.objects.Object;
import vehicle.objects.Vehicle;
import vehicle.objects.vehicles.Car;
import vehicle.objects.vehicles.Tank;
import vehicle.utils.Constants;
import vehicle.views.GameView;

import static vehicle.utils.Constants.CAR_WIDTH;

public class Road extends Area {
    private final Vehicle[] vehicles;

    public Road(float x, float y) {
        super(x, y, Constants.CANVAS_WIDTH, Constants.CAR_HEIGHT * 5);

        vehicles = new Vehicle[16];
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < vehicles.length / 2; i++) {
                createVehicle(i + j * vehicles.length / 2, y + CAR_WIDTH * (j * 2 + 1));
            }
        }
    }

    public static Area create() {
        return new Road(0, 0);
    }

    public Object[] getObjects() {
        return vehicles;
    }

    public void tick(GameView game) {
        if (game.tick % 20 == 0) {
            for (int i = 0; i < vehicles.length; i++) {
                Vehicle vehicle = vehicles[i];
                vehicle.move(-CAR_WIDTH, 0);
                if (vehicle.getX() < 0) {
                    createVehicle(i, vehicle.getY());
                }
            }
        }

        Vehicle player = game.getPlayerVehicle();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.isColliding(player)) {
                game.die();
                break;
            }
        }
    }

    public void draw(PApplet app) {
        app.fill(138, 135, 128);
        app.rect(x, y, width, height);
        for (Vehicle vehicle : vehicles) {
            vehicle.draw(app);
        }
    }

    public void createVehicle(int i, float y) {
        float furthestX = 0;
        for (Vehicle value : vehicles) {
            if (value != null && value.getX() > furthestX && value.getY() == y) {
                furthestX = value.getX();
            }
        }
        furthestX = furthestX == 0 ? x : furthestX;

        if ((int) (Math.random() * 100) < 10) {
            vehicles[i] = new Tank(furthestX + CAR_WIDTH * ((int) (Math.random() * 3) + 3) * 2, y);
        } else {
            String[] colors = Sketch.carImages.keySet().toArray(new String[0]);
            String color = null;
            while (color == null || color.equals("player")) {
                color = colors[(int) (Math.random() * colors.length)];
            }

            vehicles[i] = new Car(color, furthestX + CAR_WIDTH * ((int) (Math.random() * 3) + 3), y);
        }
    }
}
