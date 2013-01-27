package net.msgs;

public class PossessActorMsg {

  private int id;

  public PossessActorMsg() {
    this(0);
  }
  
  public PossessActorMsg(int id) {
    this.id = id;
  }

  public int getActorID() {
    return id;
  }

}
