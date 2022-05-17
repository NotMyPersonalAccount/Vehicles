package vehicle.areas;

import processing.core.PApplet;
import vehicle.objects.Object;
import vehicle.objects.Vehicle;
import vehicle.utils.Constants;
import vehicle.views.GameView;

import static vehicle.utils.Collision.rectCollision;
import static vehicle.utils.Constants.CANVAS_WIDTH;
import static vehicle.utils.Constants.CAR_WIDTH;

public class RiverArea extends Area {
    private float[] logs;

    public RiverArea(float x, float y) {
        super(x, y, CANVAS_WIDTH, Constants.CANVAS_HEIGHT / 4);

        logs = new float[]{
                CANVAS_WIDTH / CAR_WIDTH + (int) (Math.random() * 5) - 2,
                CANVAS_WIDTH / CAR_WIDTH + (int) (Math.random() * 5) - 2,
        };
    }

    public Object[] getObjects() {
        return new Object[0];
    }

    public boolean isUnsafeSpawn() {
        return true;
    }

    public static Area create() {
        return new RiverArea(0, 0);
    }

    public void tick(GameView game) {
        Vehicle player = game.getPlayerVehicle();

        if (game.tick % 25 == 0) {
            for (int i = 0; i < logs.length; i++) {
                logs[i] -= 1;
                if (logs[i] < 0) {
                    logs[i] = CANVAS_WIDTH / CAR_WIDTH + (int) (Math.random() * 3);
                    continue;
                }
                if (rectCollision((logs[i] + 1) * CAR_WIDTH, y, CAR_WIDTH, height, player.getX(), player.getY(), player.getWidth(), player.getHeight())) {
                    player.move(-CAR_WIDTH, 0);
                }
            }
        }

        if (rectCollision(x, y, width, height, player.getX(), player.getY(), player.getWidth(), player.getHeight())) {
            for (float logX : logs) {
                if (rectCollision(logX * CAR_WIDTH, y, CAR_WIDTH, height, player.getX(), player.getY(), player.getWidth(), player.getHeight())) {
                    return;
                }
            }
            game.die();
        }
    }

    public void draw(PApplet app) {
        app.fill(0, 0, 255);
        app.rect(x, y, width, height);

        for (float logX : logs) {
            app.fill(165, 42, 42);
            app.rect(logX * CAR_WIDTH, y, CAR_WIDTH, height);
        }
    }
}
