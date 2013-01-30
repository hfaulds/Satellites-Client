package actors;

import javax.media.opengl.GL3;

import maths.Vector2D;

public class Actor {

  private Vector2D position = new Vector2D();

  public Vector2D getPosition() {
    return position;
  }

  public void setPosition(Vector2D position) {
    this.position = position;
  }
  
  public void setVelocity(Vector2D velocity) {
    // TODO Auto-generated method stub
    
  }
  
  public void addForce(Vector2D force) {
    // TODO Auto-generated method stub
    
  }

  public double getRotation() {
    // TODO Auto-generated method stub
    return 0;
  }

  public void applyTorque(double d) {
    // TODO Auto-generated method stub
    
  }

  public void tick(long td) {
    // TODO Auto-generated method stub
  }

  public void render(GL3 gl) {
    // TODO Auto-generated method stub
    
  }

}