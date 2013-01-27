
import game.Vector2D;

import java.io.IOException;
import java.util.ArrayList;

import net.ClassRegistrar;
import net.ServerDetails;
import actors.Actor;
import actors.changes.ActorChange;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class StubbedGameServer {

  private final Server server = new Server();
  private ServerDetails details;
  
  public StubbedGameServer(ServerDetails details) {
    this.details = details;
  }

  public void start() throws IOException {
    server.start();
    server.bind(details.tcpPort, details.udpPort);

    ClassRegistrar msgLoader = new ClassRegistrar(server.getKryo());
    msgLoader.registerByPackage("net.msgs", "actors.changes");
    msgLoader.registerByClass(ArrayList.class, Actor.class, ActorChange.class, Vector2D.class);
  }

  public void addListener(Listener listener) {
    server.addListener(listener);
  }

  public void sendOnConnection(final Object msg) {
    Listener serverListener = new Listener() {
      @Override
      public void connected(Connection connection) {
        connection.sendTCP(msg);
      }
    };
    addListener(serverListener);
  }

  public void replyToMsgWith(final Object toReceive, final Object toSend) {
    Listener listener = new Listener() {
      @Override
      public void received(Connection connection, Object object) {
        if(object.equals(toReceive)) {
          connection.sendTCP(toSend);
        }
      }
    };
    addListener(listener);
  }

  public void close() {
    server.close();
  }


}
