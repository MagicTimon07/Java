package cc.retzlaff.timon;

import java.awt.Graphics;

public class Ai {
    public float minX;
    public float maxX;
    public float minY;
    public float maxY;
    public Vector2 min;
    public Vector2 max;
    public Guys guys;
    public int numberOfGuys;

    public Ai(float minX, float maxX, float minY, float maxY, int numberOfGuys) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
        this.numberOfGuys = numberOfGuys;
    }

    public Ai(Vector2 min, Vector2 max, int numberOfGuys) {
        this.min = min;
        this.max = max;
        this.numberOfGuys = numberOfGuys;
    }

    public void start() {
        this.guys = new Guys(this.numberOfGuys, this.minX, this.maxX, this.minY, this.maxY);
    }

    public boolean update(Graphics g, float maxDistance) {
        boolean allAreThere = this.guys.drawGuys(g);
        this.guys.update(maxDistance);
        return allAreThere;
    }

    public boolean update(Graphics g) {
        boolean allAreThere = this.guys.drawGuys(g);
        this.guys.update();
        return allAreThere;
    }
}