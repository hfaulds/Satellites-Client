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

}
