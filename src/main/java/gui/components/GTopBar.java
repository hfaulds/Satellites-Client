package gui.components;

import gui.actions.ActionEvent;
import gui.components.buttons.GButton;
import gui.listeners.ActionListener;

import javax.media.opengl.GL2;

import maths.Vector2D;
import rendering.Renderer2D;

import com.jogamp.newt.event.MouseEvent;

public class GTopBar extends GComponent {

  public static final int HEIGHT = 15;

  private final String title;
  
  private final GButton minimise = new GButton(parent, new Vector2D(parent.width - 28, parent.height + 2), 11, 11, "_");
  private final GButton close = new GButton(parent, new Vector2D(parent.width - 13, parent.height + 2), 11, 11, "X");
  
  public GTopBar(GComponent parent, String title) {
    this(parent, title, true, false);
  }

  public GTopBar(final GComponent parent, String title, boolean canMinimise, boolean bClose) {
    super(parent, new Vector2D(0, parent.height), parent.width, HEIGHT);
    parent.height += HEIGHT;
    this.title = title;
    
    if(canMinimise) {
      add(minimise);
      if(!bClose) {
        minimise.position.x += 11;
      }
    }
    
    if(bClose) {
      add(close);
    }
    
    minimise.addActionListener(new ActionListener() {
      @Override
      public void action(ActionEvent action) {
        parent.minimise();
      }
    });

    close.addActionListener(new ActionListener() {
      @Override
      public void action(ActionEvent action) {
        parent.setVisible(false);
      }
    });
    
  }
  
  
  /* Mouse Controls */
  
  @Override
  public void mouseDragged(Vector2D start, Vector2D end, Vector2D offset, MouseEvent e) {
    this.parent.position._set(end.sub(offset).sub(this.position));
    super.mouseDragged(start, end, offset, e);
  }
  
  
  /* Rendering */
  
  @Override
  public void render(GL2 gl, int width, int height) {
    Vector2D screenPosition = getScreenPosition();
    gl.glColor4d(1.0, 1.0, 1.0, 0.8);
    Renderer2D.drawFillRect(gl, 
        screenPosition.x, 
        screenPosition.y, 
        parent.width, 
        this.height);
    
    gl.glColor4d(1.0, 1.0, 1.0, 1);
    Renderer2D.drawLineRect(gl, 
        screenPosition.x, 
        screenPosition.y, 
        parent.width, 
        this.height, 
        0.9f);
    
    gl.glColor4d(0.4, 0.4, 0.4, 1.0);
    Renderer2D.drawText(gl, 
        screenPosition.x + 5, 
        screenPosition.y + 3, 
        Renderer2D.fitString(gl, title, parent.width - 10));
    
    super.render(gl, width, height);
  }

}
