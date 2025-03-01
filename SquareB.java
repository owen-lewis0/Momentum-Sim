import processing.core.PApplet;

public class SquareB extends PApplet{
    private final float mass;
    private float pos = 400;
    private final float initialVelo;
    private float velo;

    public SquareB(float m, float v){
        mass = m;
        initialVelo = v;
        velo = initialVelo;
    }

    /**Draws the square and updates the position*/
    public void make(PApplet p){
        p.fill(255);
        p.rect(pos, 450, 100, 100);
        p.fill(0);
        p.text("B", pos, 450);
        pos += velo;
    }

    /**Gets left edge of Square B so it knows where to collide*/
    public float getEdge(){
        return pos - 50;
    }

    public float getMass(){
        return mass;
    }

    public float getVelo(){
        return velo;
    }

    /**Sets the new velocity after collision or friction*/
    public void setVelo(float v){
        velo = v;
        if(velo < 0.1 && velo > -0.1)
            velo = 0;
    }

    /** Returns to initial properties*/
    public void reset(){
        pos = 400;
        velo = initialVelo;
    }
}