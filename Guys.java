package cc.retzlaff.timon;

import java.awt.Color;
import java.awt.Graphics;

public class Guys {
    public Guy[] guys;
    Vector2 vector2 = new Vector2(1.0F, 1.0F);
    public float minDist = 100.0F;

    public Guys(int numberOfGuys, Vector2 min, Vector2 max) {
        this.guys = new Guy[numberOfGuys];

        for(int i = 0; i < numberOfGuys; ++i) {
            this.guys[i] = new Guy(Vector2.generateRandomVector2InRange(min, max));
            if (this.guys[i].pos.calculateDistance(Main.player.pos) < this.minDist) {
                --i;
            }
        }

    }

    public Guys(int numberOfGuys, float minX, float maxX, float minY, float maxY) {
        this.guys = new Guy[numberOfGuys];

        for(int i = 0; i < numberOfGuys; ++i) {
            this.guys[i] = new Guy(Vector2.generateRandomVector2InRange(minX, maxX, minY, maxY));
            if (this.guys[i].pos.calculateDistance(Main.player.pos) < this.minDist) {
                --i;
            }
        }

    }

    public boolean drawGuys(Graphics g) {
        int counter = 0;
        Guy[] var3 = this.guys;
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Guy guy = var3[var5];
            if (guy.isThere) {
                ++counter;
            }

            if (guy.isThere) {
                g.setColor(Color.green);
            } else {
                g.setColor(Color.black);
            }

            g.fillOval((int)guy.pos.x, (int)guy.pos.y, 20, 20);
        }

        return this.guys.length - counter == 1;
    }

    public void update(float maxDistance) {
        Guy[] var2 = this.guys;
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Guy guy = var2[var4];
            guy.move(this.guys, maxDistance);
        }

    }

    public void update() {
        Guy[] var1 = this.guys;
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Guy guy = var1[var3];
            guy.move(this.guys);
        }

    }

    public void reset() {
        Guy[] var1 = this.guys;
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Guy guy = var1[var3];
            guy.isThere = false;
        }

    }
}

