package net;

import game.Scene;
import game.Vector2D;
import gui.Callback;

import java.io.IOException;
import java.util.ArrayList;

import net.msgs.ActorChangeMsg;
import net.msgs.PopulateSceneMsg;
import net.msgs.LoginErrorMsg;
import net.msgs.LoginRequestMsg;
import net.msgs.LoginSuccessfulMsg;
import net.msgs.PossessActorMsg;
import actors.Actor;
import actors.changes.ActorChange;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import controllers.PlayerInputController;

public class GameClient {
  
  private final Client client = new Client();
  
  private final Scene scene;
  private final PlayerInputController playerInputController;

  public GameClient(Scene scene, PlayerInputController playerInputController) {
    this.scene = scene;
    this.playerInputController = playerInputController;
  }

  public void connectToServer(ServerDetails details) throws IOException {
    setupKryo();
    
    client.start();
    
    client.addListener(new Listener() {
      @Override
      public void received(Connection connection, Object object) {
        if(object instanceof PopulateSceneMsg) {
          handleCreateSceneMsg((PopulateSceneMsg)object);
        }
        if(object instanceof PossessActorMsg) {
          handlePossessActorMsg((PossessActorMsg)object);
        }
        if(object instanceof ActorChangeMsg) {
          handleActorChangeMsg((ActorChangeMsg)object);
        }
      }
    });
    
    client.connect(5000, details.address, details.tcpPort, details.udpPort);
  }
  
  public void loginOnServer(LoginRequestMsg request, final Callback<Boolean> callback) {
    client.addListener(new Listener() {
      @Override
      public void received(Connection connection, Object object) {
        if(object instanceof LoginSuccessfulMsg) {
          callback.callback(true);
        }
        if(object instanceof LoginErrorMsg) {
          callback.callback(false);
        }
      }
    });
    
    client.sendTCP(request);  
  }

  private void handlePossessActorMsg(PossessActorMsg msg) {
    Actor actor = scene.getActor(msg.getActorID());
    playerInputController.possess(actor);
  }
  
  private void handleCreateSceneMsg(PopulateSceneMsg msg) {
    scene.addActors(msg.getActors());
  }

  private void handleActorChangeMsg(ActorChangeMsg msg) {
    Actor actor = scene.getActor(msg.getActorId());
    ActorChange change = msg.getActorChange();
    change.applyTo(actor);
  }

  private void setupKryo() {
    ClassRegistrar registrar = new ClassRegistrar(client.getKryo());
    registrar.registerByPackage("net.msgs", "actors.changes");
    registrar.registerByClass(ArrayList.class, Actor.class, ActorChange.class, Vector2D.class);
  }
  
  public void close() {
    client.close();
  }

}