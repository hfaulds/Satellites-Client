package controllers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    
    when(keyEvent.getKeyCode()).thenReturn((int) 'W');
    
    input.keyPressed(keyEvent);
    input.tick(10);
    
    verify(actor).setAcceleration(1);
  }

}
