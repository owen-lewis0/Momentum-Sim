import processing.core.PApplet;

public class SquareA extends PApplet{
    private final float mass;
    private float pos = 50;
    private final float initialVelo;
    private float velo;

    public SquareA(float m, float v){
        mass = m;
        initialVelo = v;
        velo = initialVelo;
    }

    /**Draws the square and updates the position*/
    public void make(PApplet p){
        p.rect(pos, 450, 100, 100);
        p.textAlign(CENTER);
        p.fill(0);
        p.text("A", pos, 450);
        pos += velo;
    }
    /**Gets the right edge of Square A so it knows where to collide*/
    public float getEdge(){
        return pos + 50;
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
        pos = 50;
        velo = initialVelo;
    }
}