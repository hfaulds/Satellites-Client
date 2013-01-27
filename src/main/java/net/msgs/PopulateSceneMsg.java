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
    return actors;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (!(obj instanceof PopulateSceneMsg))
      return false;
    PopulateSceneMsg other = (PopulateSceneMsg) obj;
    if (actors == null) {
      if (other.actors != null)
        return false;
    } else if (!actors.equals(other.actors))
      return false;
    return true;
  }

}
