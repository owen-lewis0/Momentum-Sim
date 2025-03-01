import processing.core.PApplet;

import java.util.Scanner;

public class Simulator extends PApplet{
    private boolean friction;
    private final SquareA squareA;
    private final SquareB squareB;
    private boolean collided = false;

    String status = "Off"; //Text for the friction button

    Scanner input = new Scanner(System.in);

    public Simulator(boolean f){
        squareA = new SquareA(getMassA(), getVeloA());
        squareB = new SquareB(getMassB(), getVeloB());
        friction = f;

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**Gets user input for mass of Square A*/
    public float getMassA(){
        System.out.println("Mass of Square A:");
        return Float.parseFloat(input.nextLine());
    }
    /**Gets user input for mass of Square B*/
    public float getMassB(){
        System.out.println("Mass of Square B:");
        return Float.parseFloat(input.nextLine());
    }
    /**Gets user input for the initial velocity of Square A*/
    public int getVeloA(){
        System.out.println("Initial velocity of Square A:");
        return Integer.parseInt(input.nextLine());
    }

    /**Gets user input for the initial velocity of Square B*/
    public int getVeloB(){
        System.out.println("Initial velocity of Square B:");
        return Integer.parseInt(input.nextLine());
    }

    /** Executes one simulation*/
    public void simulate(PApplet p){
        //Grass at bottom
        p.rectMode(CENTER);
        p.fill(0, 150, 0);
        p.rect(400, 650, 800, 300);

        //Text displays for velocity
        p.fill(0);
        p.textSize(20);
        String aVelo = squareA.getVelo() + "0";
        String bVelo = squareB.getVelo() + "0";
        p.text("Velocity of A: " + aVelo.substring(0, 4) + "m/s",100, 50);
        p.text("Velocity of B: " + bVelo.substring(0, 4) + "m/s",100, 100);
        p.noFill();

        //Start button
        p.rect(400, 75,200, 100);
        p.text("Restart", 400, 110);
        p.noFill();

        //Text for Kinetic Energy
//        p.text("KE of A = " + 0.5 * squareA.getMass() * squareA.getVelo() * squareA.getVelo() + "J", 75, 150);
//        p.text("KE of B = " + 0.5 * squareB.getMass() * squareB.getVelo() * squareB.getVelo() + "J", 75, 200);

        //Friction on/off button
        p.rect(550, 75, 100, 100);
        p.text("Friction:", 550, 45);
        p.textSize(25);
        p.text(status, 550, 85);

        //Displays both squares and handles collision
        if(friction){
            squareA.setVelo((float) (squareA.getVelo() * 0.994));
            squareB.setVelo((float) (squareB.getVelo() * 0.994));
        }
        squareA.make(p);
        squareB.make(p);
        if(squareA.getEdge() >= squareB.getEdge() && !collided){
            collide();
            collided = true;
        }
    }

    /**Updates velocity after collision, MOMENTUM FORMULA HERE*/
    public void collide(){
        float veloA = squareA.getVelo();
        float veloB = squareB.getVelo();
        float totalMomentum = (squareA.getMass()*veloA)+(squareB.getMass()*veloB);
        float newMomentum = totalMomentum / (squareA.getMass() + squareB.getMass());
        squareA.setVelo(newMomentum);
        squareB.setVelo(newMomentum);
    }

    /**Resets the default positions of the squares, also used for the initial start*/
    public void restart(){
        squareA.reset();
        squareB.reset();
        collided = false;
    }

    /** Switches the friction on and off and updates the status on the button
     * FRICTION -> NO FRICTION
     * NO FRICTION -> FRICTION
     * */
    public void toggleFriction(){
        if(friction){
            friction = false;
            status = "Off";
        }else{
            friction = true;
            status = "On";
        }
    }
}