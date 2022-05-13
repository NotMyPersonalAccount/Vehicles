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
import vehicle.views.components.Component;
import vehicle.views.components.Container;
import vehicle.views.components.Text;

import java.util.ArrayList;
import java.util.Iterator;

import static processing.core.PConstants.*;
import static vehicle.utils.Constants.*;

public class GameView extends View {
    private final ArrayList<Area> areas = new ArrayList<>();
    private final Vehicle playerVehicle = new Car(Constants.CANVAS_WIDTH / 2 - CAR_WIDTH, Constants.CANVAS_HEIGHT - CAR_HEIGHT * 3);
    private int score = 0;

    private int downCount = 0;

    private final Text scoreComponent;

    public GameView(Sketch app) {
        super(app, null);
        this.scoreComponent = new Text.Builder(app).build();
        this.components = new Component[]{new Container.Builder(app).setFixedWidth(CANVAS_WIDTH).setFixedHeight(CANVAS_HEIGHT).setDirection(Container.Direction.VERTICAL).setAlignmentX(Container.Alignment.X.RIGHT).setAlignmentY(Container.Alignment.Y.BOTTOM).setPaddingX(BASE_TEXT_SIZE).setPaddingY(BASE_TEXT_SIZE).setProperties(new Component.BaseProperties.Builder().setBackgroundColor(-1).build()).withComponents(scoreComponent).build()};
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

            a.move(0, CANVAS_HEIGHT / 512);
            a.draw(app);
        }

        playerVehicle.move(0, CANVAS_HEIGHT / 512);
        if (playerVehicle.getY() > Constants.CANVAS_HEIGHT - CAR_HEIGHT || playerVehicle.getX() < 0 || playerVehicle.getX() > Constants.CANVAS_WIDTH - CAR_WIDTH) {
            die();
            return;
        }
        playerVehicle.draw(app);

        this.scoreComponent.setText("Score: " + score);
        super.draw();
    }

    public void keyReleased() {
        if (app.key == 'p') app.setView(new PauseView(app, this));
        else if (app.key == CODED) {
            switch (app.keyCode) {
                case UP:
                    playerVehicle.move(0, -CAR_HEIGHT);
                    if (downCount > 0) {
                        downCount--;
                        break;
                    }
                    score++;
                    break;
                case DOWN:
                    playerVehicle.move(0, CAR_HEIGHT);
                    downCount++;
                    break;
                case LEFT:
                    playerVehicle.move(-CAR_HEIGHT, 0);
                    break;
                case RIGHT:
                    playerVehicle.move(CAR_HEIGHT, 0);
                    break;
            }
        }
    }

    private void die() {
        app.setView(new EndView(app, score));
    }

    private Area createArea() {
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