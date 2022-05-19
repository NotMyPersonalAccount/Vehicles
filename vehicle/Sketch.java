package vehicle;

import processing.core.PApplet;
import processing.core.PImage;
import vehicle.views.StartView;
import vehicle.views.View;

import java.util.HashMap;
import java.util.Map;

public class Sketch extends PApplet {
    public View view = new StartView(this);

    public static Map<String, PImage> carImages = new HashMap<>();
    public static Map<String, PImage> vehicleImages = new HashMap<>();

    public void settings() {
        size(800, 800);

        carImages.put("red", loadImage("red_car.png"));
        carImages.put("yellow", loadImage("yellow_car.png"));
        carImages.put("player", loadImage("player_car.png"));

        vehicleImages.put("tank", loadImage("tank.png"));
        vehicleImages.put("train", loadImage("train.png"));
    }

    public void draw() {
        background(229, 230, 228);
        pushMatrix();
        view.draw();
        popMatrix();
    }

    public void mousePressed() {
        view.mousePressed();
    }

    public void keyReleased() {
        view.keyReleased();
    }

    public void setView(View view) {
        this.view = view;
    }
}