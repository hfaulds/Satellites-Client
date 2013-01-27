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
  
}
