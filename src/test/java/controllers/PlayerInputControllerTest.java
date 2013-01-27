package controllers;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import maths.Vector2D;

import org.junit.Before;
import org.junit.Test;

import actors.Actor;

import com.jogamp.newt.event.KeyEvent;

public class PlayerInputControllerTest {
  
  private PlayerInputController input;
  private Actor actor = mock(Actor.class);
  
  @Before
  public void setup() {
    input = new PlayerInputController();
    input.possess(actor);
  }

  @Test
  public void forwardInputAcceleratesPlayer() {
    KeyEvent keyEvent = mock(KeyEvent.class);
    
    when(actor.getRotation()).thenReturn(0.0);
    when(keyEvent.getKeyCode()).thenReturn((int) 'W');
    
    input.keyPressed(keyEvent);
    input.tick(1);
    
    verify(actor).setAcceleration(eq(new Vector2D(0 , 0.00001)));
  }

}
