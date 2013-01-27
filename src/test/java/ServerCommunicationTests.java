import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import game.Scene;
import game.Vector2D;
import gui.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.GameClient;
import net.ServerDetails;
import net.msgs.ActorChangeMsg;
import net.msgs.PopulateSceneMsg;
import net.msgs.LoginErrorMsg;
import net.msgs.LoginRequestMsg;
import net.msgs.LoginSuccessfulMsg;
import net.msgs.PossessActorMsg;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import actors.Actor;
import actors.changes.ActorChange;
import actors.changes.PositionChange;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import controllers.PlayerInputController;

@SuppressWarnings("unchecked")
public class ServerCommunicationTests {

  private static final int TIMEOUT = 50;
  private static final ServerDetails SERVER_DETAILS = new ServerDetails("127.0.0.1", 54555, 54777);

  private GameClient client;
  private StubbedGameServer server;

  private PlayerInputController playerInputController = mock(PlayerInputController.class);
  private final Scene scene = mock(Scene.class);

  @Before
  public void setupFakeServer() throws IOException {
    server = new StubbedGameServer();
    server.start();
    client = new GameClient(scene, playerInputController);
  }
  
  @After
  public void tearDownFakeServer() {
    server.close();
    client.close();
  }

  @Test
  public void connectToServer() throws IOException {
    Listener serverListener = mock(Listener.class);
    server.addListener(serverListener);
    
    client.connectToServer(SERVER_DETAILS);
    
    verify(serverListener).connected(any(Connection.class));
  }
  
  @Test
  public void loginUnsuccessfully() throws IOException, InterruptedException {
    client.connectToServer(SERVER_DETAILS);
    
    
    Listener serverListener = new Listener() {
      @Override
      public void received(Connection connection, Object object) {
        connection.sendTCP(new LoginErrorMsg());
      }
    };
    server.addListener(serverListener);
    
    Callback<Boolean> callback = mock(Callback.class);
    client.loginOnServer(new LoginRequestMsg(), callback);
    
    verify(callback, timeout(TIMEOUT).times(1)).callback(false);
  }
  
  @Test
  public void loginSuccessfully() throws IOException, InterruptedException {
    Listener serverListener = new Listener() {
      @Override
      public void received(Connection connection, Object object) {
        connection.sendTCP(new LoginSuccessfulMsg());
      }
    };
    server.addListener(serverListener);
    
    Callback<Boolean> callback = mock(Callback.class);
    client.connectToServer(SERVER_DETAILS);
    client.loginOnServer(new LoginRequestMsg(), callback);
    
    verify(callback, timeout(TIMEOUT).times(1)).callback(true);
  }
  
  @Test
  public void populateScene() throws IOException {
    final List<Actor> actors = new ArrayList<Actor>();
    
    Listener serverListener = new Listener() {
      @Override
      public void connected(Connection connection) {
        connection.sendTCP(new PopulateSceneMsg(actors));
      }
    };
    server.addListener(serverListener);
    
    client.connectToServer(SERVER_DETAILS);
    
    verify(scene, timeout(TIMEOUT).times(1)).addActors(actors);
  }
  
  @Test
  public void possesActor() throws IOException {
    Actor playerActor = mock(Actor.class);
    when(scene.getActor(0)).thenReturn(playerActor);
    
    Listener serverListener = new Listener() {
      @Override
      public void connected(Connection connection) {
        connection.sendTCP(new PossessActorMsg(0));
      }
    };
    server.addListener(serverListener);
    
    client.connectToServer(SERVER_DETAILS);
    
    verify(playerInputController, timeout(TIMEOUT).times(1)).possess(playerActor);
  }
  
  @Test
  public void updateActor() throws IOException {
    Actor actor = mock(Actor.class);
    Vector2D oldPosition = mock(Vector2D.class);
    
    when(actor.getPosition()).thenReturn(oldPosition);
    when(scene.getActor(0)).thenReturn(actor);
    
    final ActorChange positionChange = new PositionChange(new Vector2D(0.5, 0.3));

    Listener serverListener = new Listener() {
      @Override
      public void connected(Connection connection) {
        connection.sendTCP(new ActorChangeMsg(0, positionChange));
      }
    };
    server.addListener(serverListener);
    
    client.connectToServer(SERVER_DETAILS);
    
    verify(oldPosition, timeout(TIMEOUT).times(1))._set(eq(new Vector2D(0.5, 0.3)));
  }
}
