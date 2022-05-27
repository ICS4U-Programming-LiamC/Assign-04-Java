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

  public float[] getAngles(float sideA, float sideB, float sideC) {
    float[] angles = new float[3];
    // returns angles from left to right, or I suppose bottom left, top, then right
    // assumes that bottom is c, left is a, and right is b
    angles[2] = 1 / (float)Math.cos((sideB * sideB + sideC * sideC - sideA * sideA) / (2 * sideB * sideC));
    angles[0] = 1 / (float)Math.cos((sideA * sideA + sideC * sideC - sideB * sideB) / (2 * sideA * sideC));
    angles[1] = 1 / (float)Math.cos((sideB * sideB + sideA * sideA - sideC * sideC) / (2 * sideB * sideA));
    return angles;
  }

  public float[] longestSide() {
    float[] returns = new float[3];
    float biggest;
    float newA;
    float newB;
    if (a > b && a > c) {
      if (b < c) {
        returns[0] = b; returns[1] = c; returns[2] = a;
      } else {
        returns[0] = c; returns[1] = b; returns[2] = a;
      }

    } else if (b > a && b > c) {
      if (a < c) {
        returns[0] = a; returns[1] = c; returns[2] = b;
      } else {
        returns[0] = c; returns[1] = a; returns[2] = b;
      }

    } else {
      if (a < b) {
        returns[0] = a; returns[1] = b; returns[2] = c;
      } else {
        returns[0] = b; returns[1] = a; returns[2] = c;
      }
    }
    return returns;
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

  public float height(float small, float small2, float hypo) {
    // assuming b is the base
    float temp = (s * (s - small) * (s - small2) * (s - hypo));
    temp = (float)Math.sqrt(temp);
    return (temp * 2 / hypo);
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
          int howMuchDelayEachTime = (int)Math.floor(totalDelayAmount / a);

          for (int delay = 0; delay < (howMuchDelayEachTime * i) - 3; delay++) {
            System.out.print(" ");
          }
          System.out.println('\\');
        }
      }

    } else {

      float[] info = longestSide();
      float aShort = info[0];
      float bShort = info[1];
      float hypo = info[2];
      float h = height(aShort, bShort, hypo);
      float x = (float)Math.sqrt((aShort * aShort) - (h * h));
      int height = (int)h;
      float[] angles = getAngles(aShort, bShort, hypo);

      for (int i = 0; i <= height; i++) {
        if (i == 0) {
          for (int j = 0; j < x; j++) {
            System.out.print("  ");
          }
          System.out.println("&");
        } else if (i == height) {
          System.out.print("&");
          for (int j = 0; j < hypo; j++) {
            System.out.print("__");
          }
          System.out.println("&");
        } else {
          System.out.println("");
        }

      }
      
    }

  }
}
