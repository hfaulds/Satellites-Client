package gui;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;

import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.util.FPSAnimator;

public class ClientGui implements GLEventListener {

  private final GLWindow window = GLWindow.create(new GLCapabilities(GLProfile.getDefault()));
  private final FPSAnimator animator = new FPSAnimator(window, 60);
  
  public ClientGui(String title, int width, int height) {
    window.setTitle(title);
    
    window.setSize(width, height);
    window.addGLEventListener(this);

    animator.start();
    window.setVisible(true);
  }

  @Override
  public void display(GLAutoDrawable drawable) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void dispose(GLAutoDrawable drawable) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void init(GLAutoDrawable drawable) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void reshape(GLAutoDrawable drawable, int arg1, int arg2, int arg3, int arg4) {
    // TODO Auto-generated method stub
    
  }
  
  
}
