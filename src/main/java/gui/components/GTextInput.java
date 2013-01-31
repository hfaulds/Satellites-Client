package gui.components;

import gui.actions.InputEntered;
import gui.listeners.ActionListener;

import javax.media.opengl.GL2;

import maths.Vector2D;
import rendering.Renderer2D;

import com.jogamp.newt.event.KeyEvent;

public class GTextInput extends GComponent {

  private static final int MAX_INPUT = 40;
  
  public String input = "";

  public GTextInput(GComponent parent, Vector2D position, int width, int height) {
    super(parent, position, width, height);
  }

  @Override
  public void render(GL2 gl, int width, int height) {
    Vector2D screenPosition = getScreenPosition();
    
    gl.glColor4d(0.6, 0.6, 0.6, 1.0);
    Renderer2D.drawFillRect(gl, 
        screenPosition.x, 
        screenPosition.y, 
        this.width, this.height
        );
    
    gl.glColor4d(1.0, 1.0, 1.0, 1.0);
    Renderer2D.drawLineRect(gl, 
        screenPosition.x ,
        screenPosition.y, 
        this.width, this.height, 0.9f
        );
    
    Renderer2D.drawText(gl, 
        screenPosition.x + 2 , 
        screenPosition.y + 5, 
        Renderer2D.fitString(gl, input, this.width)
        );
  }
  
  public void addChar(char c) {
    if(input.length() <= MAX_INPUT)
      input = input + c;
  }

  private void backSpace() {
    int length = input.length();
    if(length > 0)
      input = input.substring(0, length - 1);
  }

  public void clearInput() {
    input = "";
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int keyCode = e.getKeyCode();
    switch(keyCode) {
      case 10: // enter
        if(input.length() > 0) {
          for(ActionListener listener : actionListeners) {
            listener.action(new InputEntered(input));
          }
          clearInput();
        }
        break;
      case 8: // backspace
        backSpace();
        break;
      default:
        char character = (char)keyCode;
        if(!e.isShiftDown())
          character = Character.toLowerCase(character);
        addChar(character);
    }
  }

}
