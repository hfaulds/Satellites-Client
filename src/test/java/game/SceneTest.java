package game;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.Test;

import actors.Actor;

@SuppressWarnings("unchecked")
public class SceneTest {

  @Test
  public void populateScene() {
    Scene scene = new Scene();
    ArrayList<Actor> actors = mock(ArrayList.class);
    
    scene.populate(actors);
    scene.getActor(0);
    
    verify(actors).get(0);
  }

}
