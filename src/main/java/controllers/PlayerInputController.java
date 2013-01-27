package controllers;

import maths.Vector2D;
import actors.Actor;

import com.jogamp.newt.event.KeyEvent;

public class PlayerInputController {
  
  private static final int KEY_FORWARD = 'W';

  private static final double ACCELERATION = 0.00001;

  private Actor actor;
  private double accelMag = 0;

  public void possess(Actor actor) {
    this.actor = actor;
  }
  
  public void tick(long dt) {
    double rotation = actor.getRotation();
    Vector2D directionAsVector = new Vector2D(0,1).rotate(rotation);
    actor.setAcceleration(directionAsVector.multiply(accelMag * ACCELERATION));
  }

  public void keyPressed(KeyEvent e) {
    switch(e.getKeyCode()) {
      case KEY_FORWARD:
        accelMag = 1;
        break;
    }
  }

}
