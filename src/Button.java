import processing.core.PApplet;

public class Button extends DrawableComponent {
    PApplet p;
    static float DEFAULT_WIDTH = 100;
    static float DEFAULT_HEIGHT = 30;
    private float width = DEFAULT_WIDTH;
    private  float height = DEFAULT_HEIGHT;
    private  float x;
    private  float y;

    private boolean fitToText = true;
    private String buttonText;
    private float textWidth;
    private float textHeight;

    public Button(PApplet p, int x, int y, String buttonText) {
        this.p = p;
        this.x = x;
        this.y = y;
        this.buttonText = buttonText;
    }

    public Button(PApplet p, int x, int y, int width, int height, String buttonText) {
        this(p, x, y, buttonText);
        this.width = width;
        this.height = height;
    }

    public void calculate() {
        this.textWidth = p.textWidth(this.buttonText);
        this.textHeight = p.textAscent() + p.textDescent();

        this.width = this.textWidth;
        this.height = this.textHeight;

    }

    public void drawRectangle() {
        // Rectangle
        p.stroke(0);
        p.fill(255);
        p.rect(x, y, width, height);
        p.rectMode(p.CORNER);
    }

    public void drawText() {
        // Text
        p.fill(0);
        p.textAlign(p.CENTER);
        p.text(buttonText, x + width/2, y + height/2);
    }

    public void draw() {
        drawRectangle();
        drawText();
    }
    public boolean isClicked() {
        return (
                p.mouseX >= this.x                  // Left
                        && p.mouseX <= this.x + this.width       // Right
                        && p.mouseY >= this.y               // Top
                        && p.mouseY <= this.y + this.height // Bottom
        );
    }
}
