package vehicle.views;

import processing.sound.SoundFile;
import vehicle.Sketch;
import vehicle.areas.*;
import vehicle.objects.Vehicle;
import vehicle.objects.vehicles.Car;
import vehicle.utils.AreaCreator;
import vehicle.views.components.Component;
import vehicle.views.components.Container;
import vehicle.views.components.Text;

import java.util.ArrayList;
import java.util.Iterator;

import static processing.core.PConstants.*;
import static vehicle.utils.Constants.*;

public class GameView extends View {
    public int tick = 0;

    private final ArrayList<Area> areas = new ArrayList<>();
    private final Vehicle playerVehicle = new Car("player", CANVAS_WIDTH / 2 - CAR_WIDTH, CANVAS_HEIGHT - CAR_HEIGHT * 3);

    private boolean started = false;

    private int score = 0;
    private static int highScore = 0;

    private int downCount = 0;

    private final Text scoreComponent;
    private final Text highScoreComponent;

    public GameView(Sketch app) {
        super(app, null);
        this.scoreComponent = new Text.Builder(app).build();
        this.highScoreComponent = new Text.Builder(app).build();
        this.components = new Component[]{new Container.Builder(app).setFixedWidth(CANVAS_WIDTH).setFixedHeight(CANVAS_HEIGHT).setDirection(Container.Direction.VERTICAL).setAlignmentX(Container.Alignment.X.RIGHT).setAlignmentY(Container.Alignment.Y.BOTTOM).setPaddingX(BASE_TEXT_SIZE).setPaddingY(BASE_TEXT_SIZE).setProperties(new Component.BaseProperties.Builder().setBackgroundColor(-1).build()).withComponents(highScoreComponent, scoreComponent).build()};

        createMap();
    }

    public void draw() {
        System.out.println("frame: " + app.frameRate + " count" + areas.size());
        boolean shouldTick = started && app.view == this;

        SoundFile backgroundMusic = Sketch.sounds.get("background");
        if (backgroundMusic.isPlaying() && !shouldTick) {
            backgroundMusic.pause();
        } else if (!backgroundMusic.isPlaying() && shouldTick) {
            backgroundMusic.play();
        }

        if (shouldTick) {
            tick++;
            createMap();

            playerVehicle.move(0, CANVAS_HEIGHT / 512);
            if (playerVehicle.getY() > CANVAS_HEIGHT - CAR_HEIGHT || playerVehicle.getX() < 0 || playerVehicle.getX() > CANVAS_WIDTH - CAR_WIDTH) {
                die();
                return;
            }
        }

        Iterator<Area> it = areas.iterator();
        while (it.hasNext()) {
            Area a = it.next();
            if (shouldTick) {
                if (a.getY() >= CANVAS_HEIGHT) {
                    it.remove();
                    continue;
                }

                a.move(0, CANVAS_HEIGHT / 512);
                a.tick(this);
            }
            a.draw(app);
        }

        playerVehicle.draw(app);

        this.scoreComponent.setText("Score: " + score);
        this.highScoreComponent.setText("High Score: " + highScore);
        super.draw();
    }

    public void keyReleased() {
        if (app.key == 'p') app.setView(new PauseView(app, this));
        else if (app.key == CODED) {
            started = true;
            switch (app.keyCode) {
                case UP:
                    playerVehicle.move(0, -CAR_HEIGHT);
                    if (downCount > 0) {
                        downCount--;
                        break;
                    }
                    score++;
                    if (score > highScore) highScore = score;
                    break;
                case DOWN:
                    playerVehicle.move(0, CAR_HEIGHT);
                    downCount++;
                    break;
                case LEFT:
                    playerVehicle.move(-CAR_WIDTH, 0);
                    break;
                case RIGHT:
                    playerVehicle.move(CAR_WIDTH, 0);
                    break;
            }
        }
    }

    public Vehicle getPlayerVehicle() {
        return playerVehicle;
    }

    public void die() {
        app.setView(new EndView(app, score));
    }

    private Area createArea() {
        AreaCreator[] callable = {Spawn::create, Desert::create, River::create, Road::create, Railroad::create};
        return callable[(int) app.random(callable.length)].call();
    }

    private void createMap() {
        float y;
        if (areas.size() == 0) {
            y = CANVAS_HEIGHT;
        } else {
            Area topArea = areas.get(areas.size() - 1);
            y = topArea.getY();
        }

        while (true) {
            Area a = createArea();

            if (!a.checkSpawnConditions(areas)) continue;

            y -= a.height;
            if (y < -a.height) {
                break;
            }
            a.move(0, y);
            areas.add(a);
        }
    }
}