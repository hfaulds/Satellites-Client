package game;

import static org.mockito.Mockito.*;


import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import controllers.Controller;

import actors.Actor;

@SuppressWarnings("unchecked")
public class SceneTest {

  Scene scene;
  
  @Before
  public void setup() {
    scene = new Scene();
  }
  
  @Test
  public void tickIsPassedToActors() {
    ArrayList<Actor> actors = new ArrayList<Actor>();
    Actor actor = mock(Actor.class);
    actors.add(actor);
    scene.populate(actors);
    scene.tick((long) 0.0);
    
    verify(actor).tick((long) 0.0);;
  }
  
  @Test
  public void tickIsPassedToControllers() {
    Controller controller = mock(Controller.class);
    scene.addController(controller);

    scene.tick((long) 0.0);
    
    verify(controller).tick((long)0.0);
  }

}
