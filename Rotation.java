package cc.retzlaff.timon;

public class Rotation {
    public float[] rotation = new float[3];

    public Rotation(float[] rotation) {
        if (rotation.length == 3) {
            this.rotation = rotation;
        } else {
            throw new IllegalStateException("More or less than 3 elements in rotation array. " + rotation.length);
        }
    }

    public Rotation(float rotationX, float rotationY, float rotationZ) {
        this.rotation[0] = rotationX;
        this.rotation[1] = rotationY;
        this.rotation[2] = rotationZ;
    }
}

