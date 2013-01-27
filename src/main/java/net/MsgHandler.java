package net;

import game.Scene;
import net.msgs.ActorChangeMsg;
import net.msgs.PopulateSceneMsg;
import net.msgs.PossessActorMsg;
import actors.Actor;
import actors.changes.ActorChange;
import controllers.PlayerInputController;

public class MsgHandler {
  
  public Scene scene;
  public PlayerInputController playerInputController;

  public MsgHandler(Scene scene, PlayerInputController playerInputController) {
    this.scene = scene;
    this.playerInputController = playerInputController;
  }

  public void handlePossessActorMsg(PossessActorMsg msg) {
    Actor actor = scene.getActor(msg.getActorID());
    playerInputController.possess(actor);
  }
  
  public void handlePopulateSceneMsg(PopulateSceneMsg msg) {
    scene.addActors(msg.getActors());
  }

  public void handleActorChangeMsg(ActorChangeMsg msg) {
    Actor actor = scene.getActor(msg.getActorId());
    ActorChange change = msg.getActorChange();
    change.applyTo(actor);
  }
}