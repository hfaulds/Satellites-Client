
import game.Vector2D;

import java.io.IOException;
import java.util.ArrayList;

import net.ClassRegistrar;
import actors.Actor;
import actors.changes.ActorChange;

import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class StubbedGameServer {

  private final Server server = new Server();
  
  public void start() throws IOException {
    server.start();
    server.bind(54555, 54777);

    ClassRegistrar msgLoader = new ClassRegistrar(server.getKryo());
    msgLoader.registerByPackage("net.msgs", "actors.changes");
    msgLoader.registerByClass(ArrayList.class, Actor.class, ActorChange.class, Vector2D.class);
  }

  public void close() {
    server.close();
  }

  public void addListener(Listener listener) {
    server.addListener(listener);
  }

}
