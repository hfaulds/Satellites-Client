package game;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import game.Vector2D;
import gui.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.GameClient;
import net.MsgHandler;
import net.ServerDetails;import net.TestServer;

import net.msgs.ActorChangeMsg;
import net.msgs.LoginErrorMsg;
import net.msgs.LoginRequestMsg;
import net.msgs.LoginSuccessfulMsg;
import net.msgs.PopulateSceneMsg;
import net.msgs.PossessActorMsg;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import actors.Actor;
import actors.changes.ActorChange;
import actors.changes.PositionChange;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

@SuppressWarnings("unchecked")
public class GameClientTest {

  private static final int TIMEOUT = 50;
  private static final ServerDetails SERVER_DETAILS = new ServerDetails("127.0.0.1", 54555, 54777);

  private GameClient client;
  private TestServer server;

  private MsgHandler handler = mock(MsgHandler.class);

  @Before
  public void setupFakeServer() throws IOException {
    server = new TestServer(SERVER_DETAILS);
    server.start();
    client = new GameClient(handler);
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
    LoginRequestMsg msg = new LoginRequestMsg("username", "password");
    server.replyToMsgWith(msg, new LoginErrorMsg());
    Callback<Boolean> callback = mock(Callback.class);
    
    client.connectToServer(SERVER_DETAILS);
    client.loginOnServer(msg, callback);
    
    verify(callback, timeout(TIMEOUT).times(1)).callback(false);
  }

  
  @Test
  public void loginSuccessfully() throws IOException, InterruptedException {
    LoginRequestMsg msg = new LoginRequestMsg("username", "password");
    server.replyToMsgWith(msg, new LoginSuccessfulMsg());
    Callback<Boolean> callback = mock(Callback.class);
    
    client.connectToServer(SERVER_DETAILS);
    client.loginOnServer(msg, callback);

    verify(callback, timeout(TIMEOUT).times(1)).callback(true);
  }
  
  @Test
  public void populateScene() throws IOException {
    final List<Actor> actors = new ArrayList<Actor>();
    final PopulateSceneMsg msg = new PopulateSceneMsg(actors);
    server.sendOnConnection(msg);
    
    client.connectToServer(SERVER_DETAILS);
    
    verify(handler, timeout(TIMEOUT).times(1)).handlePopulateSceneMsg(eq(msg));
  }
  
  @Test
  public void possesActor() throws IOException {
    final PossessActorMsg msg = new PossessActorMsg(0);
    server.sendOnConnection(msg);
    
    client.connectToServer(SERVER_DETAILS);
    
    verify(handler, timeout(TIMEOUT).times(1)).handlePossessActorMsg(eq(msg));
  }
  
  @Test
  public void updateActor() throws IOException {
    final ActorChange positionChange = new PositionChange(new Vector2D(0.5, 0.3));
    final ActorChangeMsg msg = new ActorChangeMsg(0, positionChange);
    server.sendOnConnection(msg);
    
    client.connectToServer(SERVER_DETAILS);

    verify(handler, timeout(TIMEOUT).times(1)).handleActorChangeMsg(eq(msg));
  }
}
