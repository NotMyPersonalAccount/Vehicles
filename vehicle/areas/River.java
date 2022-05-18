package vehicle.areas;

import processing.core.PApplet;
import vehicle.objects.Vehicle;
import vehicle.utils.Constants;
import vehicle.views.GameView;

import static vehicle.utils.Collision.rectCollision;
import static vehicle.utils.Constants.CANVAS_WIDTH;
import static vehicle.utils.Constants.CAR_WIDTH;

public class River extends Area {
    private final float[] logs;

    public River(float x, float y) {
        super(x, y, CANVAS_WIDTH, Constants.CAR_WIDTH * 5);

        logs = new float[8];
        for (int i = 0; i < logs.length; i++) {
            float logX = (int) (Math.random() * 4) + 3;
            if (i > 0) {
                logX = logs[i - 1] + (int) (Math.random() * 4) + 3;
            }
            logs[i] = logX;
        }
    }

    public static Area create() {
        return new River(0, 0);
    }

    public void tick(GameView game) {
        Vehicle player = game.getPlayerVehicle();

        if (game.tick % 25 == 0) {
            for (int i = 0; i < logs.length; i++) {
                logs[i] -= 1;
                if (logs[i] < 0) {
                    float furthestX = 0;
                    for (float logX : logs) {
                        if (logX > furthestX) {
                            furthestX = logX;
                        }
                    }
                    logs[i] = furthestX + (int) (Math.random() * 4) + 3;

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
