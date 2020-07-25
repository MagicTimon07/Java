package cc.retzlaff.timon;

import java.util.Arrays;
import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class Vector2 implements Comparable<Vector2> {
    float x;
    float y;
    float[] position = new float[2];

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
        this.position[0] = x;
        this.position[1] = y;
    }

    public Vector2(Vector2 vector2) {
        this.x = vector2.x;
        this.y = vector2.y;
        this.position[0] = this.x;
        this.position[1] = this.y;
    }

    public static float generateRandomFloatInRange(float min, float max) {
        return min + (new Random()).nextFloat() * (max - min);
    }

    public static Vector2 generateRandomVector2InRange(float minX, float maxX, float minY, float maxY) {
        float x = generateRandomFloatInRange(minX, maxX);
        float y = generateRandomFloatInRange(minY, maxY);
        return new Vector2(x, y);
    }

    public static Vector2 generateRandomVector2InRange(Vector2 min, Vector2 max) {
        float x = generateRandomFloatInRange(min.x, max.x);
        float y = generateRandomFloatInRange(min.y, max.y);
        return new Vector2(x, y);
    }

    public float calculateDistance(Vector2 v) {
        float x = this.x - v.x;
        float y = this.y - v.y;
        return (float)Math.sqrt((double)(x * x + y * y));
    }

    public int compareTo(@NotNull Vector2 vector2) {
        return Float.compare(this.x + this.y, vector2.x + vector2.y);
    }

    public String posToString() {
        return Arrays.toString(this.position);
    }

    public String toString() {
        return "Vector2{x=" + this.x + ", y=" + this.y + '}';
    }
}

