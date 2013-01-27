package actors.changes;

import maths.Vector2D;
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
    actor.setPosition(position);
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
