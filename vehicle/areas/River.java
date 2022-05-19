package vehicle.areas;

import processing.core.PApplet;
import vehicle.Sketch;
import vehicle.objects.Object;
import vehicle.objects.Vehicle;
import vehicle.objects.plants.Log;
import vehicle.utils.Constants;
import vehicle.views.GameView;

import java.util.ArrayList;

import static vehicle.utils.Constants.CANVAS_WIDTH;
import static vehicle.utils.Constants.CAR_WIDTH;

public class River extends Area {
    private final Log[] logs;

    public River(float x, float y) {
        super(x, y, CANVAS_WIDTH, Constants.CAR_WIDTH * 5);

        logs = new Log[8];
        for (int i = 0; i < logs.length; i++) {
            float logX = ((int) (Math.random() * 4) + 3) * CAR_WIDTH;
            if (i > 0) {
                logX += logs[i - 1].getX();
            }
            logs[i] = new Log(logX, y);
        }
    }

    public static Area create() {
        return new River(0, 0);
    }

    public Object[] getObjects() {
        return logs;
    }

    public void tick(GameView game) {
        Vehicle player = game.getPlayerVehicle();

        if (game.tick % 25 == 0) {
            for (int i = 0; i < logs.length; i++) {
                if (logs[i].isColliding(player)) {
                    player.move(-CAR_WIDTH, 0);
                }
                logs[i].move(-CAR_WIDTH, 0);
                if (logs[i].getX() < 0) {
                    float furthestX = 0;
                    for (Log log : logs) {
                        if (log.getX() > furthestX) {
                            furthestX = log.getX();
                        }
                    }
                    logs[i] = new Log((furthestX + (int) (Math.random() * 4) + 3) * CAR_WIDTH, y);
                }
            }
        }

        if (isColliding(player)) {
            for (Log log : logs) {
                if (log.isColliding(player)) {
                    return;
                }
            }
            game.die();
        }
    }

    public void draw(PApplet app) {
        for (float waterX = x; waterX < x + width; waterX += Constants.CAR_WIDTH) {
            for (float waterY = y; waterY < y + height; waterY += Constants.CAR_HEIGHT) {
                app.image(Sketch.backgroundImages.get("water"), waterX, waterY, Constants.CAR_WIDTH, Constants.CAR_HEIGHT);
            }
        }
        
        for (Log log : logs) {
            log.draw(app);
        }
    }

    public boolean checkSpawnConditions(ArrayList<Area> areas) {
        return super.checkSpawnConditions(areas) && !(areas.get(areas.size() - 1) instanceof Railroad);
    }
}
