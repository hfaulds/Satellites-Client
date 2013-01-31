package gui;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.util.FPSAnimator;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GLProfile.class, GLCapabilities.class, GLWindow.class, ClientGui.class})
public class ClientGuiTest {
  
  private GLWindow glWindow = mock(GLWindow.class);
  private FPSAnimator animator = mock(FPSAnimator.class);
  
  @Before
  public void mockGLWindow() throws Exception {
    //Mock out all the classes and functions required to intialize GLWindow
    mockStatic(GLProfile.class);
    when(GLProfile.getDefault()).thenReturn(mock(GLProfile.class));
    
    mockStatic(GLCapabilities.class);
    whenNew(GLCapabilities.class).withAnyArguments().thenReturn(mock(GLCapabilities.class));
    
    mockStatic(GLWindow.class);
    when(GLWindow.create(any(GLCapabilities.class))).thenReturn(glWindow);

    mockStatic(FPSAnimator.class);
    whenNew(FPSAnimator.class).withAnyArguments().thenReturn(animator);
  }

  @Test
  public void setVisibleTrueSetGlWindowVisibleTrue() {
    ClientGui gui = new ClientGui("title", 200, 200);
    
    gui.setVisible(true);
    
    verify(glWindow, times(1)).setVisible(true);
  }
  
  @Test
  public void setVisibleFalseSetGlWindowVisibleFalse() {
    ClientGui gui = new ClientGui("title", 200, 200);
    
    gui.setVisible(false);
    
    verify(glWindow, times(1)).setVisible(false);
  }
  
  @Test
  public void setVisibleTrueStartsAnimator() {
    ClientGui gui = new ClientGui("title", 200, 200);
    
    gui.setVisible(true);
    
    verify(animator, times(1)).start();
  }

  @Test
  public void setVisibleFalseStopsAnimator() {
    ClientGui gui = new ClientGui("title", 200, 200);
    
    gui.setVisible(false);
    
    verify(animator, times(1)).stop();
  }


}
