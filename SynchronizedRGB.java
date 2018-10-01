//SynchronizedRGB  -- DONT RUN IN CLASS

public class SynchronizedRGB {

    // Values must be between 0 and 255.

    private int red;

    private int green;

    private int blue;

    private String name;

    private void check(int red, int green, int blue) {

        if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
            throw new IllegalArgumentException();
        }
    }

    public SynchronizedRGB(int red, int green, int blue, String name) {

        check(red, green, blue);
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.name = name;
    }

    public void set(int red, int green, int blue, String name) {

        check(red, green, blue);
        synchronized (this) {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.name = name;
        }
    }

    public synchronized int getRGB() {
        return ((red << 16) | (green << 8) | blue);
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized void invert() {
        red = 255 - red;
        green = 255 - green;
        blue = 255 - blue;
        name = "Inverse of " + name;
    }

}

/**
        ASSUME THREAD 1 RUNS

        SynchronizedRGB color = new SynchronizedRGB(0, 0, 0, "Pitch Black");

        THEN

        int myColorInt = color.getRGB();        //Statement 1
        String myColorName = color.getName();   //Statement 2

        //PROBLEM IF RAN AS
        int myColorInt = color.getRGB();        //  T1  --  Statement 1
        color.set()                             //  T2  --  Runs the method before T1 statement 2
        String myColorName = color.getName();   //  T1  --  Statement 2

        //colors won't match
 */