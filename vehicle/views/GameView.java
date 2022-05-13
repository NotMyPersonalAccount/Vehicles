package vehicle.views;

import vehicle.Sketch;
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

import static processing.core.PConstants.*;
import static vehicle.utils.Constants.CAR_HEIGHT;
import static vehicle.utils.Constants.CAR_WIDTH;

public class GameView extends View {
    private final ArrayList<Area> areas = new ArrayList<>();
    private final Vehicle playerVehicle = new Car(Constants.CANVAS_WIDTH / 2 - CAR_WIDTH, Constants.CANVAS_HEIGHT - CAR_HEIGHT);

    public GameView(Sketch app) {
        super(app, null);
    }

    public void draw() {
        createMap();

        Iterator<Area> it = areas.iterator();
        while (it.hasNext()) {
            Area a = it.next();
            if (a.y >= Constants.CANVAS_HEIGHT) {
                it.remove();
                continue;
            }

            a.move(0, 1);
            a.draw(app);
        }

        playerVehicle.move(0, 1);
        playerVehicle.draw(app);
    }

    public void keyPressed() {
        if (app.key == 'p') app.setView(new PauseView(app, this));
        else if(app.key == CODED){
            switch(app.keyCode){
                case UP:
                    playerVehicle.move(0, -5);
                    break;
                case DOWN:
                    playerVehicle.move(0, 5);
                    break;
                case LEFT:
                    playerVehicle.move(-5, 0);
                    break;
                case RIGHT:
                    playerVehicle.move(5, 0);
                    break;
            }
        }
    }

    public Area createArea() {
        AreaCreator[] callable = {GrassArea::create, RiverArea::create, DesertArea::create};
        Area a = null;
        while (a == null || (areas.size() > 0 && a.getClass() == areas.get(areas.size() - 1).getClass())) {
            a = callable[(int) app.random(callable.length)].call();
        }
        return a;
    }

    private void createMap() {
        float y;
        if (areas.size() == 0) {
            y = Constants.CANVAS_HEIGHT;
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