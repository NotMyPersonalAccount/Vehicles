package vehicle.areas;

import processing.core.PApplet;
import vehicle.Sketch;
import vehicle.objects.Vehicle;
import vehicle.objects.vehicles.Car;
import vehicle.utils.Constants;
import vehicle.views.GameView;

import static vehicle.utils.Collision.rectCollision;
import static vehicle.utils.Constants.CAR_WIDTH;

public class Road extends Area {
    private final Car[] cars;

    public Road(float x, float y) {
        super(x, y, Constants.CANVAS_WIDTH, Constants.CAR_HEIGHT * 5);

        cars = new Car[16];
        for (int j = 0; j < 2; j++) {
            float carX = x;
            for (int i = 0; i < cars.length / 2; i++) {
                carX += CAR_WIDTH * ((int) (Math.random() * 3) + 3);
                cars[i + j * cars.length / 2] = new Car(chooseColor(), carX, y + CAR_WIDTH * (j * 2 + 1));
            }
        }
    }

    public static Area create() {
        return new Road(0, 0);
    }

    public void tick(GameView game) {
        if (game.tick % 20 == 0) {
            for (int i = 0; i < cars.length; i++) {
                Car car = cars[i];
                car.move(-CAR_WIDTH, 0);
                if (car.getX() < 0) {
                    float furthestX = 0;
                    for (Car value : cars) {
                        if (value.getX() > furthestX && value.getY() == car.getY()) {
                            furthestX = value.getX();
                        }
                    }

                    cars[i] = new Car(chooseColor(), furthestX + CAR_WIDTH * ((int) (Math.random() * 3) + 3), car.getY());
                }
            }
        }

        Vehicle player = game.getPlayerVehicle();
        for (Car car : cars) {
            if (rectCollision(car.getX(), car.getY(), CAR_WIDTH, CAR_WIDTH, player.getX(), player.getY(), CAR_WIDTH, CAR_WIDTH)) {
                game.die();
                break;
            }
        }
    }

    public void draw(PApplet app) {
        app.fill(138, 135, 128);
        app.rect(x, y, width, height);
        for (Car car : cars) {
            car.draw(app);
        }
    }

    public void move(float x, float y) {
        super.move(x, y);
        for (Car car : cars) {
            car.move(x, y);
        }
    }

    public String chooseColor() {
        String[] colors = Sketch.carImages.keySet().toArray(new String[0]);
        String color = null;
        while (color == null || color.equals("player")) {
            color = colors[(int) (Math.random() * colors.length)];
        }
        return color;
    }
}
