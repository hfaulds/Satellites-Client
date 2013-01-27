package maths;


public class Vector2D {
  
  public double x;
  public double y;
  public static double Z;
  
  /* Constructors */
  
  public Vector2D() {
    this(0,0);
  }

  public Vector2D(Vector2D other) {
    this(other.x, other.y);
  }
  
  public Vector2D(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  /* Utility Functions */
  @Override
  public String toString() {
    return "(" + this.x + "," + this.y + ")";
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof Vector2D))
      return false;
    Vector2D other = (Vector2D) obj;
    if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
      return false;
    if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
      return false;
    return true;
  }
  
  

}
