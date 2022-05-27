import java.lang.Math;

import javax.crypto.spec.IvParameterSpec;

public class Triangle {
  float a, b, c, s;
  String triangleType;

  public Triangle() {
    this.a = 3;
    this.b = 4;
    this.c = 5;
    s = (a + b + c) / 2;
    isValid();
    this.triangleType = typeOfTriangle();
  }

  public Triangle(float a, float b, float c) {
    this.a = a;
    this.b = b;
    this.c = c;
    s = (a + b + c) / 2;
    isValid();
    this.triangleType = typeOfTriangle();
  }

  private boolean isValid() {
    if (a <= 0 || b <= 0 || c <= 0) {
      this.a = Math.abs(a);
      this.b = Math.abs(b);
      this.c = Math.abs(c);
      return true;
    } else {
      return false;
    }
  }

  public float semiPerimeter() {
    return s;
  }

  public float area() {
    float temp = (s * (s - a) * (s - b) * (s - c));
    return (float)Math.sqrt(temp);
  }

  public float perimeter() {
    return (a + b + c);
  }

  public String typeOfTriangle() {
    if (a == b && b == c) {
      return "equilateral";
    } else if (a == b || b == c || c == a) {
      return "isosceles";
    } else {
      float biggest;
      float newA;
      float newB;
      if (a > b && a > c) {
        biggest = a;
        newA = b;
        newB = c;
      } else if (b > a && b > c) {
        biggest = b;
        newA = a;
        newB = c;
      } else {
        biggest = c;
        newA = b;
        newB = a;
      }
      if (biggest == Math.sqrt(Math.pow(newA, 2) + Math.pow(newB, 2))) {
        return "right angle";
      } else {
        return "scalene";
      }
    }
  }

  public float height() {
    // assuming b is the base
    float temp = (s * (s - a) * (s - b) * (s - c));
    temp = (float)Math.sqrt(temp);
    return (temp * 2 / b);
  }

  public void printTrangle() {
    // takes points A as (0, 0)
    // and B as vertex (?, bases height)
    if (triangleType.equals("right angle")) {
      float[] vertex1 = {0f, 0f};
      float[] vertex2 = {0f, a};
      float[] vertex3 = {b, 0f};

      for (int i = 0; i < a + 2; i++) {
        if (i == 0) {
          System.out.println("&");
        } else if (i == a + 1) {
          System.out.print("&");
          for (int j = 0; j < b; j++) {
            System.out.print("___");
          }
          System.out.println("&");
        } else {
          System.out.print("|");
          float totalDelayAmount = b * 3;
          int howMuchDelayEachTime = Math.floor(totalDelayAmount / a);

          for (int delay = 0; delay < i; delay++) {
            System.out.print("   ");
          }
          System.out.println('\\');
        }
      }

    }

  }

}
