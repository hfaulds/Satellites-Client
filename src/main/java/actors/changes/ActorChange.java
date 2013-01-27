package actors.changes;

import actors.Actor;

public interface ActorChange {

  void applyTo(Actor actor);

}
