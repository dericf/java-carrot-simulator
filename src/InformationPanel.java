import processing.core.PApplet;

import java.util.ArrayList;

public class InformationPanel extends DrawableComponent {
    private PApplet p;
    private int x;
    private int y;
    private int height;
    private int width;
    private float averageLength = 0;

    private ArrayList<Carrot> carrots;

    public InformationPanel(PApplet p) {
        this.p = p;
        this.height = 50;
        this.width = p.width;
        this.x = 0;
        this.y = p.height - height;
    }

    public InformationPanel(PApplet p, ArrayList<Carrot> carrots) {
        this(p);
        this.carrots = carrots;

    }

    public void calculate() {
        float sum = 0;
        for (Carrot c :
                carrots) {
            sum += c.getLength();
        }
        if (this.carrots.size() == 0) {
            this.averageLength = 0;

        } else {
            this.averageLength = sum / carrots.size();
        }
    }

    public void drawText() {
        p.fill(255);
        p.stroke(255);
        p.textAlign(p.LEFT);

        String text = String.format("Total Carrots: %d | Average Length: %.3f", carrots.size(), averageLength);
        p.text(text, x + 10, y + height/2f);
    }

    public void draw() {
        this.calculate();

        p.noStroke();
        p.fill(0, 0, 0);

//        p.fill(0x5a, 0x3c, 0x13);
        p.rectMode(p.CORNER);
        p.rect(x, y, width, height);
        drawText();
    }
}
