package game;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import javax.media.opengl.GL3;

import org.junit.Before;
import org.junit.Test;

import actors.Actor;
import controllers.Controller;

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

  @Test
  public void renderIsPassedToActors() throws ClassNotFoundException {
    ArrayList<Actor> actors = new ArrayList<Actor>();
    Actor actor = mock(Actor.class);
    actors.add(actor);

    GL3 gl = mock(GL3.class);
    
    scene.populate(actors);
    scene.render(gl);

    verify(actor).render(gl);
  }

}
