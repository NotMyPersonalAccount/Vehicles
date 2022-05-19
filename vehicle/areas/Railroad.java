package vehicle.areas;

import processing.core.PApplet;
import vehicle.objects.Object;
import vehicle.objects.Vehicle;
import vehicle.objects.vehicles.Train;
import vehicle.views.GameView;

import java.util.ArrayList;

import static vehicle.utils.Constants.*;

public class Railroad extends Area {
    private final Train train;

    public Railroad(float x, float y) {
        super(x, y, CANVAS_WIDTH, CAR_HEIGHT);
        train = new Train(CANVAS_WIDTH * ((int) (Math.random() * 10) + 10), y);
    }

    public static Area create() {
        return new Railroad(0, 0);
    }

    public Object[] getObjects() {
        return new Object[]{train};
    }

    public void tick(GameView game) {
        train.move(-(CANVAS_WIDTH / 20), 0);
        Vehicle player = game.getPlayerVehicle();
        if (train.isColliding(player)) {
            game.die();
        }
    }

    public void draw(PApplet app) {
        app.fill(138, 135, 128);
        app.rect(x, y, width, height);
        train.draw(app);

        if (train.getX() < CANVAS_WIDTH * 4 && train.getX() > -train.getWidth()) {
            if (app.frameCount % 15 == 0) {
                if (app.frameCount % 2 == 0) app.fill(255, 0, 0);
                app.rect(x, y, width, 5);
                app.rect(x, y + height - 5, width, 5);
            }
        }
    }

    public boolean checkSpawnConditions(ArrayList<Area> areas) {
        return super.checkSpawnConditions(areas) && !(areas.get(areas.size() - 1) instanceof River);
    }
}
