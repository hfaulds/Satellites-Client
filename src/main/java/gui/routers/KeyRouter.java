package gui.routers;

import gui.components.GComponent;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;

public class KeyRouter implements KeyListener {

  private final GComponent component;

  public KeyRouter(GComponent component) {
    this.component = component;
  }

  @Override
  public void keyReleased(KeyEvent e) {
    component.keyReleased(e);
  }

  @Override
  public void keyPressed(KeyEvent e) {
    component.keyPressed(e);
  }

  @Override
  public void keyTyped(KeyEvent e) {}
}