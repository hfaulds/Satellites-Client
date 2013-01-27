package game;

import java.util.List;

import actors.Actor;


public class Scene {

  private List<Actor> actors;

  public void addActors(List<Actor> actors) {
    this.actors = actors;
  }

  public Actor getActor(int index) {
    return actors.get(0);
  }

}
