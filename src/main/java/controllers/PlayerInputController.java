package controllers;

import com.jogamp.newt.event.KeyEvent;

import actors.Actor;

public class PlayerInputController {
  
  private static final int KEY_FORWARD = 'W';

  private Actor actor;
  
  private double accelMag = 0;

  public void possess(Actor actor) {
    this.actor = actor;
  }
  
  public void tick(long dt) {
    actor.setAcceleration(accelMag);
  }


  public void keyPressed(KeyEvent e) {
    switch(e.getKeyCode()) {
      case KEY_FORWARD:
        accelMag = 1;
        break;
    }
  }

}
