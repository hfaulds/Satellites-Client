package actors;

import javax.media.opengl.GL3;

import maths.Vector2D;

public class Actor {

  private Vector2D position;
  private Vector2D velocity;
  private Vector2D acceleration;

  public Vector2D getPosition() {
    return position;
  }

  public void setPosition(Vector2D position) {
    this.position = position;
  }
  
  public Vector2D getVelocity() {
    return velocity;
  }
  
  public void setVelocity(Vector2D velocity) {
    this.velocity = velocity;
  }
  
  public Vector2D getAcceleration() {
    return acceleration;
  }
  
  public void setAcceleration(Vector2D acceleration) {
    this.acceleration = acceleration;
  }

  public double getRotation() {
    return 0;
  }

  public void setRotationAccelleration(double d) {
    // TODO Auto-generated method stub
    
  }

  public void tick(long td) {
    // TODO Auto-generated method stub
    
  }

  public void render(GL3 gl) {
    // TODO Auto-generated method stub
    
  }

}