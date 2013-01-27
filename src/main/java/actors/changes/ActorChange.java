package actors.changes;

import actors.Actor;

public interface ActorChange {

  public void applyTo(Actor actor);
  public boolean equals(Object object);

}
