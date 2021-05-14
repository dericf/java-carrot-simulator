import processing.core.PApplet;

abstract class DrawableComponent {
    private PApplet p;
    public DrawableComponent() {}
    public DrawableComponent(PApplet p) {
        this.p = p;
    }

    public abstract void calculate();
    public abstract void draw();
}
