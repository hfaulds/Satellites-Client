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
    Actor actor = mock(Actor.class);
    when(actor.getPosition()).thenReturn(mock(Vector2D.class));
    Vector2D newPosition = mock(Vector2D.class);

    PositionChange change = new PositionChange(newPosition);
    change.applyTo(actor);
    
    verify(actor).setPosition(newPosition);
  }

}
