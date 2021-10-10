package flocking;

import processing.core.PVector;

public class Boid {


    private PVector previousPosition;

    private PVector position;
    private PVector velocity = new PVector((float)Math.random() * 4 - 2, (float)Math.random() * 4 - 2);
    private PVector acceleration = new PVector();

    public Boid(PVector position){
        this.position = position;
        this.previousPosition = position.copy();
    }

    private void move(){
        this.velocity.limit(4);
        this.handleBorderCollision();
        this.previousPosition = this.position.copy();
        this.position.add(this.velocity);
    }
    private void accelerate(){
        this.velocity.add(acceleration);
    }

    public void updatePosition(Boid[] neighbors) {

        //steering vektoren
        PVector separation = new PVector();
        PVector alignment = new PVector();
        PVector cohesion = new PVector();

        int neighborcount = 0;

        for(Boid neighbor : neighbors){
            float distance = this.position.dist(neighbor.getPosition());
            if(distance > 0 && distance < 200){
                neighborcount++;
                alignment.add(neighbor.getVelocity());
                cohesion.add(neighbor.getPosition());
                separation.add(this.getPosition().copy().sub(neighbor.getPosition()).div(distance));

            }
        }

        if(neighborcount > 0){
            this.acceleration.add(separation.div(neighborcount).normalize().mult(0.13f));
            this.acceleration.add(alignment.div(neighborcount).normalize().mult(0.01f));
            this.acceleration.add(cohesion.div(neighborcount).sub(this.position).normalize().mult(0.16f));
        }

        accelerate();
        move();

        this.acceleration.mult(0);

    }

    public PVector getDirection(){
        return this.position.sub(this.previousPosition).normalize();
    }

    private void handleBorderCollision(){
        if(this.position.x <= 0) this.position.x = View.DISPLAY_WIDTH;
        if(this.position.x > View.DISPLAY_WIDTH) this.position.x = 0;
        if(this.position.y <= 0) this.position.y = View.DISPLAY_HEIGHT;
        if(this.position.y > View.DISPLAY_HEIGHT) this.position.y = 0;
    }

    public PVector getPosition() {
        return position;
    }

    public void setPosition(PVector position) {
        this.position = position;
    }

    public PVector getVelocity() {
        return velocity;
    }

    public void setVelocity(PVector velocity) {
        this.velocity = velocity;
    }

    public PVector getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(PVector acceleration) {
        this.acceleration = acceleration;
    }

    @Override
    public String toString() {
        return "Boid{" +
                "position=" + position +
                ", velocity=" + velocity +
                ", acceleration=" + acceleration +
                '}';
    }
}
