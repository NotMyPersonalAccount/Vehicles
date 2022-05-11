package vehicle.areas;

import processing.core.PApplet;
import vehicle.objects.Object;

abstract public class Area {
    public float x;
    public float y;
    public float width;
    public float height;

    public Area(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    abstract public Object[] getObjects();

    abstract public void draw(PApplet app);

    public void move(float x, float y) {
        this.x += x;
        this.y += y;
        for (Object o : getObjects()) {
            o.move(x, y);
        }
    }
}
