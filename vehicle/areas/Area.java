package vehicle.areas;

import processing.core.PApplet;
import vehicle.objects.Object;
import vehicle.views.GameView;

abstract public class Area extends Object {
    public float width;
    public float height;

    public Area(float x, float y, float width, float height) {
        super(x, y);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Object[] getObjects() {
        return new Object[0];
    }

    abstract public void tick(GameView game);


    abstract public void draw(PApplet app);

    public void move(float x, float y) {
        this.x += x;
        this.y += y;
        for (Object o : getObjects()) {
            o.move(x, y);
        }
    }
}
