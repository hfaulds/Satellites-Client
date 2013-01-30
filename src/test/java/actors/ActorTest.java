package actors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import maths.Vector2D;

import org.junit.Before;
import org.junit.Test;

public class ActorTest {

  private Actor actor;
  
  @Before
  public void setup() {
    actor = new Actor();
  }
  
  @Test
  public void setAndGetPosition() {
    Vector2D position = mock(Vector2D.class);
    actor.setPosition(position);
    assertThat(actor.getPosition(), is(position));
  }

}
