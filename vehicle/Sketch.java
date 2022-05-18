package vehicle;

import processing.core.PApplet;
import processing.core.PImage;
import vehicle.views.StartView;
import vehicle.views.View;

import java.util.HashMap;
import java.util.Map;

import static vehicle.utils.Constants.CAR_HEIGHT;
import static vehicle.utils.Constants.CAR_WIDTH;

public class Sketch extends PApplet {
    public View view = new StartView(this);

    public static Map<String, PImage> carImages = new HashMap<>();

    public void settings() {
        size(800, 800);

        carImages.put("red", loadImage("red_car.png"));
        carImages.put("yellow", loadImage("yellow_car.png"));
        for(PImage carImage : carImages.values()) {
            carImage.resize((int)CAR_WIDTH, (int)CAR_HEIGHT);
        }
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