package flocking;

import processing.core.PVector;

public class Flock {

    public static final int BOID_NUMBER = 300;
    Boid[] boids = new Boid[BOID_NUMBER];

    public Flock(){
        for(int i = 0; i < BOID_NUMBER; i++){
            boids[i] = new Boid(new PVector((float)Math.random() * View.DISPLAY_WIDTH, (float)Math.random() * View.DISPLAY_HEIGHT));
        }
    }

    public void updateFlock(){
        for(Boid boid : this.boids){
            boid.updatePosition(this.boids);
        }
    }

}
