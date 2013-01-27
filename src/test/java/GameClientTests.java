import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import game.Scene;

import java.io.IOException;
import java.util.ArrayList;

import net.MsgHandler;
import net.msgs.ActorChangeMsg;
import net.msgs.PopulateSceneMsg;
import net.msgs.PossessActorMsg;

import org.junit.Before;
import org.junit.Test;

import actors.Actor;
import actors.changes.ActorChange;
import controllers.PlayerInputController;

@SuppressWarnings("unchecked")
public class GameClientTests {

  private PlayerInputController playerInputController = mock(PlayerInputController.class);
  private final Scene scene = mock(Scene.class);
  
  private MsgHandler handler;

  @Before
  public void setupFakeServer() throws IOException {
    handler = new MsgHandler(scene, playerInputController);
  }
  
  @Test
  public void populateScene() throws IOException {
    PopulateSceneMsg msg = mock(PopulateSceneMsg.class);
    ArrayList<Actor> actors = mock(ArrayList.class);
    
    when(msg.getActors()).thenReturn(actors);
    
    handler.handlePopulateSceneMsg(msg);
    
    verify(scene).addActors(actors);
  }
  
  @Test
  public void possessActor() throws IOException {
    PossessActorMsg msg = mock(PossessActorMsg.class);
    Actor actor = mock(Actor.class);
    
    when(msg.getActorID()).thenReturn(0);
    when(scene.getActor(0)).thenReturn(actor);
    
    handler.handlePossessActorMsg(msg);
    
    verify(playerInputController).possess(actor);
  }
  
  @Test
  public void updateActor() throws IOException {
    ActorChangeMsg msg = mock(ActorChangeMsg.class);
    Actor actor = mock(Actor.class);
    ActorChange change = mock(ActorChange.class);
    
    when(msg.getActorChange()).thenReturn(change );
    when(scene.getActor(0)).thenReturn(actor);
    
    handler.handleActorChangeMsg(msg);
    
    verify(change).applyTo(actor);
  }
}

