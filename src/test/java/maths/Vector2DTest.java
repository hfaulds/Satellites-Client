package maths;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class Vector2DTest {

  /* Mathematical functions */

  @Test
  public void rotate() {
    Vector2D result = new Vector2D(1, 0).rotate(Math.toRadians(180));
    Vector2D expected = new Vector2D(-1, 0);
    
    double errorX = result.x - expected.x;
    double errorY = result.y - expected.y;
    
    double accuracy = 1e-15;
    assertThat(errorX, lessThanOrEqualTo(accuracy));
    assertThat(errorY, lessThanOrEqualTo(accuracy));
  }

  @Test
  public void multiply() {
    assertThat(new Vector2D(1, 2).multiply(2), equalTo(new Vector2D(2, 4)));
  }

  @Test
  public void add() {
    assertThat(new Vector2D(1, 2).add(new Vector2D(10, 5)), equalTo(new Vector2D(11, 7)));
  }
  
}
