import processing.core.PApplet;

import java.util.concurrent.TimeUnit;

public class Carrot {
    PApplet p;
    // Max Lengths
    static final float MAX_LENGTH = 250;
    static final float MAX_TOP_LENGTH = 60;

    // Max Diameters
    static final float MAX_D_NECK = 100;
    static final float MAX_D_BOTTOM = 15;
    static final float MAX_D_TOP = 5;

    // Age
    static final float MAX_AGE_MS = 1000 * 30;
    static final float MAX_AGE_DAYS = 3;

    // Position
    private float starting_x = 100;
    private float starting_y = 200;
    private float length = 0;
    private float top_length = 0;
    private float diam_neck;
    private float diam_bottom;
    private float diam_top;

    // Age/Time
    private long time0;
    private float age = 0;

    // Units
    private final Units units = Units.CM;

    private boolean isSeed = true;

    // Display
    private String infoString;

    public Carrot(PApplet p) {
        this.p = p;
    }

    public Carrot(PApplet p, int x, int y) {
        this(p);

        this.starting_x = x;
        this.starting_y = y;
    }

    public void calculate() {
        //
        // Make all calculations here before drawing
        //
        long now = System.currentTimeMillis();

        this.age = now - this.time0;
        long ageInDays = TimeUnit.SECONDS.convert((long)age, TimeUnit.MILLISECONDS);

        float aid =  this.age / (1000 * 60 * 60 * 24);
        this.infoString = "Born: " + this.time0 + "\nNow: " + now + "\nAge: " + age + "\nAgeInDays: " + aid +
                "\nlength: " + length + "\nDiam Top: " + diam_top;


        if (this.age < Carrot.MAX_AGE_MS) {
            this.grow();
        }
    }

    public float getAgeInDays() {
        return this.age/(1000 * 60 * 60 * 24);
    }

    public void plant() {
        //
        // Initiates the growing of the carrot and sets the current time
        //
        this.time0 = System.currentTimeMillis();
        this.isSeed = false;
    }

    public void grow() {
        //
        // Increase all parts of the carrot proportional to age/maxAge
        //
        float fraction = (this.age/MAX_AGE_MS);
        this.length = (Carrot.MAX_LENGTH * fraction);
        this.top_length = (Carrot.MAX_TOP_LENGTH * fraction);
        this.diam_top = (Carrot.MAX_D_TOP * fraction);
        this.diam_neck = (Carrot.MAX_D_NECK * fraction);
        this.diam_bottom = (Carrot.MAX_D_BOTTOM * fraction);
    }

    public void growToDay(int day) {
        this.time0 = (System.currentTimeMillis() - ((long)day * 1000));
    }

    public void drawSeed() {
        //
        // Draw just a single seed
        //
        p.noStroke();
        p.fill(199, 199, 199);
        p.ellipse(starting_x, starting_y, 10, 5);

    }

    public void draw() {
        //
        // First check if it is still a seed
        //
        if (isSeed) {
            drawSeed();
            return;
        }
        //
        // Run pre-draw calculations
        //
        this.calculate();
        //
        // Display the Info String
        //
        p.stroke(0);
        p.fill(255);

        p.noStroke();
        p.fill(255, 100, 50);
        //
        // Neck
        //
        p.circle(starting_x, starting_y, diam_neck);
        //
        // Bottom
        //
        p.circle(starting_x, starting_y + length, diam_bottom);
        //
        // Body of the carrot
        //
        p.quad(
                starting_x - diam_neck / 2, starting_y,
                starting_x + diam_neck / 2, starting_y,
                starting_x + diam_bottom / 2, starting_y + length,
                starting_x - diam_bottom / 2, starting_y + length
        );
        //
        // Green Top
        //
        p.fill(0, 210, 40);
        // Top
        p.circle(starting_x, starting_y, diam_top);

        p.quad(
                starting_x - diam_top / 2, starting_y,
                starting_x + diam_top / 2, starting_y,
                starting_x + diam_top / 3, starting_y - top_length,
                starting_x - diam_top / 3, starting_y - top_length);

    }

    public float getLength() {
        return length;
    }
}