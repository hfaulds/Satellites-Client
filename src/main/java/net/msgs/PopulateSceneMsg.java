package net.msgs;


import java.util.List;

import actors.Actor;


public class PopulateSceneMsg {

  private final List<Actor> actors;

  public PopulateSceneMsg() {
    this(null);
  }
  
  public PopulateSceneMsg(List<Actor> actors) {
    this.actors = actors;
  }

  public List<Actor> getActors() {
    // TODO Auto-generated method stub
    return actors;
  }

}
