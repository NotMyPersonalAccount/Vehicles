package vehicle.utils;

public class Collision {
    public static boolean rectCollision(float x, float y, float w, float h, float x2, float y2, float w2, float h2) {
        return x < x2 + w2 && x + w > x2 && y < y2 + h2 && y + h > y2;
    }
}
