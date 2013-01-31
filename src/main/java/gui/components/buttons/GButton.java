package gui.components.buttons;

import gui.actions.ButtonClick;
import gui.components.GComponent;
import gui.listeners.ActionListener;

import javax.media.opengl.GL2;

import maths.Vector2D;
import rendering.Renderer2D;

import com.jogamp.newt.event.MouseEvent;

public class GButton extends GComponent {

  protected final String label;
  protected Vector2D textPos;
  
  public GButton(GComponent parent, Vector2D position, int width, int height, String label) {
    super(parent, position, width, height);
    this.label = label;
  }
  
  @Override
  public void init(GL2 gl, int width, int height) {
    Vector2D center = new Vector2D(this.width, this.height).divide(2);
    Vector2D text = Renderer2D.getTextSize(gl, label).divide(2);
    textPos = center.sub(text);
  }
  
  @Override
  public void render(GL2 gl, int width, int height) {
    Vector2D screenPosition = getScreenPosition();
    gl.glColor4d(0.3, 0.3, 0.3, 1.0);
    Renderer2D.drawFillRect(gl, screenPosition.x, screenPosition.y, this.width, this.height);
    
    gl.glColor4d(1.0, 1.0, 1.0, 1.0);
    Renderer2D.drawText(gl, screenPosition.x + textPos.x, screenPosition.y + textPos.y, label);
  }

  @Override
  public void mouseReleased(Vector2D mouse, MouseEvent e) {
    super.mouseReleased(mouse, e);
    for(ActionListener listener : actionListeners) {
      listener.action(new ButtonClick());
    }
  }
}
