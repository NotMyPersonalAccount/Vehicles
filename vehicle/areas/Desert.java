package vehicle.areas;

import processing.core.PApplet;
import vehicle.Sketch;
import vehicle.objects.Object;
import vehicle.objects.Vehicle;
import vehicle.objects.plants.Cacti;
import vehicle.utils.Constants;
import vehicle.views.GameView;

import java.util.ArrayList;

public class Desert extends Area {
    private final ArrayList<Cacti> cacti = new ArrayList<>();

    public Desert(float x, float y) {
        super(x, y, Constants.CANVAS_WIDTH, Constants.CAR_HEIGHT * 8);

        for (float cactiX = x; cactiX < x + width; cactiX += Constants.CAR_WIDTH) {
            for (float cactiY = y; cactiY < y + height; cactiY += Constants.CAR_HEIGHT) {
                if ((int) (Math.random() * 100) < 20) {
                    cacti.add(new Cacti(cactiX, cactiY));
                }
            }
        }
    }

    public static Area create() {
        return new Desert(0, 0);
    }

    public Object[] getObjects() {
        return cacti.toArray(new Object[0]);
    }

    public void tick(GameView game) {
        Vehicle player = game.getPlayerVehicle();
        for (Cacti cacti : cacti) {
            if (cacti.isColliding(player)) {
                game.die();
                return;
            }
        }
    }

    public void draw(PApplet app) {
        app.image(Sketch.backgroundImages.get("sand"), x, y);
        for (Cacti cacti : cacti) {
            cacti.draw(app);
        }
    }
}
