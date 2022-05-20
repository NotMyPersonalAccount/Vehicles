package vehicle;

import processing.core.PApplet;
import processing.core.PImage;
import processing.sound.SoundFile;
import vehicle.views.StartView;
import vehicle.views.View;

import java.util.HashMap;
import java.util.Map;

public class Sketch extends PApplet {
    public View view = new StartView(this);

    public static Map<String, PImage> carImages = new HashMap<>();
    public static Map<String, PImage> vehicleImages = new HashMap<>();
    public static Map<String, PImage> backgroundImages = new HashMap<>();

    public static Map<String, SoundFile> sounds = new HashMap<>();

    public void settings() {
        size(800, 800);

        carImages.put("red", loadImage("red_car.png"));
        carImages.put("yellow", loadImage("yellow_car.png"));
        carImages.put("player", loadImage("player_car.png"));

        vehicleImages.put("tank", loadImage("tank.png"));
        vehicleImages.put("train", loadImage("train.png"));

        backgroundImages.put("grass", loadImage("grass.jpg"));
        backgroundImages.put("sand", loadImage("sand.jpg"));
        backgroundImages.put("water", loadImage("water.jpg"));
        backgroundImages.put("rail", loadImage("rail.png"));
        backgroundImages.put("rail_alert", loadImage("rail_alert.png"));
        backgroundImages.put("log", loadImage("log.jpg"));

        sounds.put("background", new SoundFile(this, "background.mp3"));
        sounds.get("background").loop();
        sounds.put("button_click", new SoundFile(this, "button_click.mp3"));
    }

    public void draw() {
        background(0);
        pushMatrix();
        noStroke();
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