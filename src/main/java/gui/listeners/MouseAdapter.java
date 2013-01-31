package gui.listeners;

import maths.Vector2D;

import com.jogamp.newt.event.MouseEvent;

public class MouseAdapter implements MouseListener {

  @Override
  public void mousePressed(Vector2D click, MouseEvent e) {}

  @Override
  public void mouseReleased(Vector2D click, MouseEvent e) {}

  @Override
  public void mouseDragged(Vector2D start, Vector2D end, Vector2D offset, MouseEvent e) {}

  @Override
  public void mouseMoved(Vector2D mouse) {}

  @Override
  public void mouseWheelMoved(MouseEvent e) {}

}
