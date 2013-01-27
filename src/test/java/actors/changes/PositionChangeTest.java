package actors.changes;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import maths.Vector2D;

import org.junit.Test;

import actors.Actor;

public class PositionChangeTest {
  
  @Test
  public void applyTo() {
    Vector2D newPosition = mock(Vector2D.class);
    PositionChange change = new PositionChange(newPosition);
    
    Actor actor = mock(Actor.class);
    Vector2D oldPosition = mock(Vector2D.class);
    
    when(actor.getPosition()).thenReturn(oldPosition);
    
    change.applyTo(actor);
    
    verify(oldPosition)._set(newPosition);
  }

}
