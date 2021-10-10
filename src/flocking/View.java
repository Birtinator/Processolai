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

            /*
             * https://processing.org/tutorials/transform2d
             * rotate() and translate() move the coordinate system, not the object!
             * Correctly rotating by:
             * 1. Translate the coordinate system's origin to where the Boid should be
             * 2. Rotating the whole coordinate system
             * 3. Drawing the Boid at the origin
             */

            float theta = boid.getVelocity().heading();
            fill(244);
            pushMatrix();
            translate(boid.getPosition().x, boid.getPosition().y);
            rotate(theta);
            ellipse(0, 0, 21, 10);
            popMatrix();


        }
        flock.updateFlock();


        //  System.out.println(flock.boids[0]);

    }

    public static void main(String[] args) {
        PApplet.main("flocking.View");
    }
}
