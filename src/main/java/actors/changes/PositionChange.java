package actors.changes;

import game.Vector2D;
import actors.Actor;


public class PositionChange implements ActorChange {

  private Vector2D position;

  public PositionChange() {
    
  }
  
  public PositionChange(Vector2D position) {
    this.position = position;
  }
  
  @Override
  public void applyTo(Actor actor) {
    actor.getPosition()._set(position);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (!(obj instanceof PositionChange))
      return false;
    PositionChange other = (PositionChange) obj;
    if (position == null) {
      if (other.position != null)
        return false;
    } else if (!position.equals(other.position))
      return false;
    return true;
  }


}
