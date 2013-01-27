package controllers;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
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
    when(actor.getRotation()).thenReturn(0.0);
  }

  @Test
  public void forwardInputAcceleratesPlayer() {
    KeyEvent keyEvent = mock(KeyEvent.class);
    when(keyEvent.getKeyCode()).thenReturn((int) 'W');
    
    input.keyPressed(keyEvent);
    input.tick(1);
    
    verify(actor).setAcceleration(eq(new Vector2D(0 , 0.00001)));
  }

  @Test
  public void leftInputSpinsPlayerClockwise() {
    KeyEvent keyEvent = mock(KeyEvent.class);
    when(keyEvent.getKeyCode()).thenReturn((int) 'A');
    
    input.keyPressed(keyEvent);
    input.tick(1);
    
    verify(actor).setRotationAccelleration(0.0001);
  }
  
  @Test
  public void rightInputSpinsPlayerCounterClockwise() {
    KeyEvent keyEvent = mock(KeyEvent.class);
    when(keyEvent.getKeyCode()).thenReturn((int) 'D');
    
    input.keyPressed(keyEvent);
    input.tick(1);
    
    verify(actor).setRotationAccelleration(-0.0001);
  }
  
  @Test
  public void accelerationIsRemovedWhenKeyIsLifted() {
    KeyEvent keyEvent = mock(KeyEvent.class);
    when(keyEvent.getKeyCode()).thenReturn((int) 'W');
    
    input.keyPressed(keyEvent);
    
    int ticksWhileKeyDown = (int) Math.random();
    for(int i=0; i < ticksWhileKeyDown; i++)
      input.tick(1);
    
    input.keyReleased(keyEvent);
    input.tick(1);
    
    verify(actor, times(ticksWhileKeyDown)).setAcceleration(eq(new Vector2D(0 , 0.00001)));
  }
}
