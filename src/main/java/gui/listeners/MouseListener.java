package gui.listeners;

import maths.Vector2D;

import com.jogamp.newt.event.MouseEvent;

public interface MouseListener {

  public void mousePressed(Vector2D click, MouseEvent e);
  public void mouseReleased(Vector2D click, MouseEvent e);
  public void mouseDragged(Vector2D start, Vector2D end, Vector2D offset, MouseEvent e);
  public void mouseMoved(Vector2D mouse);
  public void mouseWheelMoved(MouseEvent e);
  
}
