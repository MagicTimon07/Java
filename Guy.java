package cc.retzlaff.timon;

import static java.lang.Float.POSITIVE_INFINITY;

public class Guy {
    Vector2 pos;
    public boolean isThere = false;

    public Guy(Vector2 pos) {
        this.pos = new Vector2(pos);
        Stringy.print("Created Guy at " + pos.posToString());
    }

    public Guy(Vector2 min, Vector2 max) {
        this.pos = Vector2.generateRandomVector2InRange(min, max);
        Stringy.print("Created Guy at " + this.pos.posToString());
    }

    public void move(Guy[] guys, float maxDistance) {
        boolean stop = false;
        float smallestDistance = maxDistance;
        int smallestDistanceIndex = 0;

        float distance;
        for(int i = 0; i < guys.length; ++i) {
            if (guys[i] != this) {
                distance = this.pos.calculateDistance(guys[i].pos);
                if (distance < smallestDistance) {
                    smallestDistanceIndex = i;
                    smallestDistance = distance;
                }
            }
        }

        float distBottom = (float)Main.windowSizeY - this.pos.y;
        distance = (float)Main.windowSizeX - this.pos.x;
        if (smallestDistance <= 20.0F) {
            this.isThere = true;
        }

        if (!this.isThere) {
            Vector2 var10000 = this.pos;
            var10000.x += (guys[smallestDistanceIndex].pos.x - this.pos.x) / smallestDistance * Main.botSpeed;
            var10000 = this.pos;
            var10000.y += (guys[smallestDistanceIndex].pos.y - this.pos.y) / smallestDistance * Main.botSpeed;
        }

    }

    public void move(Guy[] guys) {
        Vector2 player = Main.player.pos;
        float smallestDistance = POSITIVE_INFINITY;
        int index = 0;

        for(int i = 0; i < guys.length; ++i) {
            if (guys[i] != this) {
                float distance = this.pos.calculateDistance(guys[i].pos);
                if (distance < smallestDistance) {
                    index = i;
                    smallestDistance = distance;
                }
            }
        }

        if (smallestDistance <= 20.0F && !guys[index].isThere) {
            this.isThere = true;
        }

        if (smallestDistance > 20.0F) {
            this.isThere = false;
        }

        if (!this.isThere) {
            if (this.pos.calculateDistance(player) <= 20.0F) {
                Main.player.setDead(true);
                return;
            }

            Vector2 var10000 = this.pos;
            var10000.x += (player.x - this.pos.x) / this.pos.calculateDistance(player) * Main.botSpeed;
            var10000 = this.pos;
            var10000.y += (player.y - this.pos.y) / this.pos.calculateDistance(player) * Main.botSpeed;
        }

    }
}
