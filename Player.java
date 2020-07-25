package cc.retzlaff.timon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Player {
    public Vector2 pos;
    private boolean isDead = false;
    public float speed = 1.5F;
    private boolean movingForward = false;
    private boolean movingRight = false;
    private boolean movingLeft = false;
    private boolean movingBackward = false;

    public Player(Vector2 pos, float speed) {
        this.pos = pos;
        this.speed = speed;
    }

    public void setDead(boolean dead) {
        this.isDead = dead;
    }

    public boolean isDead() {
        return this.isDead;
    }

    public void update(Graphics g) {
        Vector2 var10000;
        if (this.movingForward && this.pos.y > 0.0F) {
            var10000 = this.pos;
            var10000.y -= this.speed;
        }

        if (this.movingBackward && this.pos.y < (float)(Main.windowSizeY - 20)) {
            var10000 = this.pos;
            var10000.y += this.speed;
        }

        if (this.movingRight && this.pos.x < (float)(Main.windowSizeX - 20)) {
            var10000 = this.pos;
            var10000.x += this.speed;
        }

        if (this.movingLeft && this.pos.x > 0.0F) {
            var10000 = this.pos;
            var10000.x -= this.speed;
        }

        g.setColor(Color.red);
        g.fillOval((int)this.pos.x, (int)this.pos.y, 20, 20);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'w') {
            this.movingForward = true;
        } else if (e.getKeyChar() == 'a') {
            this.movingLeft = true;
        } else if (e.getKeyChar() == 'd') {
            this.movingRight = true;
        } else if (e.getKeyChar() == 's') {
            this.movingBackward = true;
        }

    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'w') {
            this.movingForward = false;
        } else if (e.getKeyChar() == 'a') {
            this.movingLeft = false;
        } else if (e.getKeyChar() == 'd') {
            this.movingRight = false;
        } else if (e.getKeyChar() == 's') {
            this.movingBackward = false;
        }

    }

    public void reset() {
        this.setDead(false);
        this.pos = new Vector2((float)(Main.windowSizeX / 2), (float)(Main.windowSizeY / 2));
    }
}

