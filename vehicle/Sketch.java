package vehicle;

import processing.core.PApplet;
import vehicle.views.StartView;
import vehicle.views.View;

public class Sketch extends PApplet {
    public View view = new StartView(this);

    public void settings() {
        size(800, 800);
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