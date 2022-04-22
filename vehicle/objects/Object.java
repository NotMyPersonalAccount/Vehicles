package vehicle.objects;

import processing.core.PApplet;

abstract public class Object {
    protected float x;
    protected float y;

    public Object(float x, float y){
        this.x = x;
        this.y = y;
    }


    public boolean isCollidable() {
        return true;
    }

    public boolean isColliding(Object other) {
        return isCollidable() && other.isCollidable() && getX() < other.getX() + other.getWidth() && getX() + getWidth() > other.getX() && getY() < other.getY() + other.getHeight() && getY() + getHeight() > other.getY();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    abstract public float getWidth();

    abstract public float getHeight();

    public void move(float x, float y) {
        this.x += x;
        this.y += y;
    }

    abstract public void draw(PApplet app);
}
