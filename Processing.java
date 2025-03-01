import processing.core.PApplet;
import processing.core.PImage;

public class Processing extends PApplet{
    private Simulator simulation;
    PImage restartBtn;

    public void settings(){
        size(800, 800);
    }

    public void setup() {
        simulation = new Simulator(false);
        restartBtn = loadImage("restartButton.png");
    }

    public void draw(){
        imageMode(CENTER);
        restartBtn.resize(40, 40);

        background(255);
        image(restartBtn, 400, 75);

        simulation.simulate(this);
    }

    public void mouseClicked(){
        if(mouseButton == LEFT){
            if(mouseX >= 300 && mouseX <= 500 && mouseY >= 25 && mouseY <= 125)
                simulation.restart();
            if(mouseX > 500 && mouseX <= 600 && mouseY >= 25 && mouseY <= 125)
                simulation.toggleFriction();
        }
    }
}