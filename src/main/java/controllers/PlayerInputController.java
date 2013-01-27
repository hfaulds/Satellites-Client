package controllers;

import maths.Vector2D;
import actors.Actor;

import com.jogamp.newt.event.KeyEvent;

public class PlayerInputController {
  
  private static final int KEY_FORWARD = 'W';
  private static final int KEY_LEFT = 'A';
  private static final int KEY_RIGHT = 'D';

  private static final double ACCELERATION = 0.00001;
  private static final double ROTATIONAL_ACCELERATION = 0.0001;


  private Actor actor;
  
  private double forwardImpetus = 0;
  private double rotationImpetus = 0;

  public void possess(Actor actor) {
    this.actor = actor;
  }
  
  public void tick(long dt) {
    if(forwardImpetus != 0) {
      double rotation = actor.getRotation();
      Vector2D directionAsVector = new Vector2D(0,1).rotate(rotation);
      actor.setAcceleration(directionAsVector.multiply(ACCELERATION));
    }
    if(rotationImpetus != 0) {
      actor.setRotationAccelleration(rotationImpetus * ROTATIONAL_ACCELERATION);
    }
  }

  public void keyPressed(KeyEvent e) {
    switch(e.getKeyCode()) {
      case KEY_FORWARD:
        forwardImpetus = 1;
        break;
      case KEY_LEFT:
        rotationImpetus = 1;
        break;
      case KEY_RIGHT:
        rotationImpetus = -1;
        break;
    }
  }

  public void keyReleased(KeyEvent e) {
    switch(e.getKeyCode()) {
      case KEY_FORWARD:
        forwardImpetus = 0;
        break;
      case KEY_LEFT:
      case KEY_RIGHT:
        rotationImpetus = 0;
        break;
    }
  }

}
