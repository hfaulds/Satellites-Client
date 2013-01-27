package game;

import java.util.ArrayList;
import java.util.List;

import actors.Actor;
import controllers.Controller;

public class Scene {

  private List<Actor> actors = new ArrayList<Actor>();
  private List<Controller> controllers = new ArrayList<Controller>();

  public void populate(List<Actor> actors) {
    this.actors = actors;
  }
  
  public void addController(Controller controller) {
    controllers.add(controller);
  }

  public Actor getActor(int index) {
    return actors.get(index);
  }

  public void tick(long td) {
    for(Controller controller : controllers)
      controller.tick(td);
    for(Actor actor : actors)
      actor.tick(td);
  }


}
