package vehicle.areas;

import processing.core.PApplet;
import vehicle.objects.Object;

abstract public class Area {
    protected f;

    abstract public Object[] getObjects();

    abstract public void draw(PApplet app);

    public void move(float x, float y) {
        for (Object o : getObjects()) {
            o.move(x, y);
        }
    }
}
