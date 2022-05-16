package vehicle.areas;

import processing.core.PApplet;
import vehicle.objects.Object;
import vehicle.views.GameView;

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

    public boolean isUnsafeSpawn() {
        return false;
    }

    abstract public void draw(PApplet app);

    public void move(float x, float y) {
        this.x += x;
        this.y += y;
        for (Object o : getObjects()) {
            o.move(x, y);
        }
    }
}
