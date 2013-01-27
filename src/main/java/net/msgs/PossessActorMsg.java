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

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (!(obj instanceof PossessActorMsg))
      return false;
    PossessActorMsg other = (PossessActorMsg) obj;
    if (id != other.id)
      return false;
    return true;
  }

}
