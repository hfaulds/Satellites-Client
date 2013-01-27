package net.msgs;

import actors.changes.ActorChange;

public class ActorChangeMsg {

  private final int id;
  private final ActorChange change;

  public ActorChangeMsg() {
    this(0, null);
  }

  public ActorChangeMsg(int id, ActorChange change) {
    this.id = id;
    this.change = change;
  }

  public ActorChange getActorChange() {
    return change;
  }

  public int getActorId() {
    return id;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (!(obj instanceof ActorChangeMsg))
      return false;
    ActorChangeMsg other = (ActorChangeMsg) obj;
    if (change == null) {
      if (other.change != null)
        return false;
    } else if (!change.equals(other.change))
      return false;
    if (id != other.id)
      return false;
    return true;
  }

}
