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

  
  /* Mathematical functions */
  
  public Vector2D rotate(double angle) {
    double sin = Math.sin(angle);
    double cos = Math.cos(angle);
    double x = this.x*cos - this.y*sin;
    double y = this.x*sin + this.y*cos;
    return new Vector2D(x,y);
  }

  public Vector2D multiply(double accelMag) {
    return new Vector2D(this.x * accelMag, this.y * accelMag);
  }
  
  public Vector2D add(Vector2D other) {
    return new Vector2D(this.x + other.x, this.y + other.y);
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
