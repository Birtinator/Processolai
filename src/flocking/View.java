package flocking;

import processing.core.PApplet;

public class View extends PApplet {

    public static int DISPLAY_WIDTH;
    public static int DISPLAY_HEIGHT;

    Flock flock;


    @Override
    public void settings() {
        DISPLAY_WIDTH = displayWidth;
        DISPLAY_HEIGHT = displayHeight;
        size(displayWidth, displayHeight);
    }


    @Override
    public void setup() {
        flock = new Flock();
    }

    @Override
    public void draw() {
        background(0);
        for(Boid boid : this.flock.boids){
            fill(244);
            ellipse(boid.getPosition().x, boid.getPosition().y, 21, 10);
            rotate((float)Math.atan2(boid.getDirection().x, boid.getDirection().y)); //teufelszeug
            
        }
        flock.updateFlock();
      //  System.out.println(flock.boids[0]);

    }

    public static void main(String[] args) {
        PApplet.main("flocking.View");
    }
}
