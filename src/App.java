import processing.core.PApplet;

import java.util.ArrayList;

public class App extends PApplet {
    private Carrot carrot;
    private Button plantButton;
    private Button resetButton;
    private ArrayList<Carrot> carrots;
    private InformationPanel infoPanel;

    public void setup() {
        frameRate(30);
        this.carrots = new ArrayList<>();
        this.carrot = new Carrot(this);
        this.plantButton = new Button(this, 500, 20, "Plant");
        this.resetButton = new Button(this, 0, 0, "Reset");
        this.infoPanel = new InformationPanel(this, carrots);

    }

    public void settings() {
        size(800, 800);
    }

    public void draw(){
        background(0x5a, 0x3c, 0x13);
        resetButton.draw();
        infoPanel.draw();

        Carrot seed = new Carrot(this, mouseX, mouseY);
        seed.draw();

        for (Carrot c : carrots) {
            c.draw();
        }
    }

    public void mouseClicked() {
        if (resetButton.isClicked()) {
            carrots.clear();
        } else {
            Carrot newCarrot = new Carrot(this, mouseX, mouseY);
            newCarrot.plant();
            this.carrots.add(newCarrot);
        }
    }

    public static void main(String[] passedArgs) {
        String[] appletArgs = new String[] { "App" }; // "App" has to be the name of the main app
        PApplet.main(appletArgs);
    }
}