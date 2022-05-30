import java.lang.Math;

/////////////// TO DO LIST ///////////////////////////////
// clean up methods, many could use class parameters instead of passed variables
// comment
// header
// test against online websites


public class Triangle {
  double rToD = 180 / Math.PI;
  double a, b, c, s;
  double angleA, angleB, angleC;
  String triangleType;
  double[] sides = new double[3];
  double[] angles;
  boolean valid;

  public Triangle() {
    sides[0] = 3;
    sides[1] = 4;
    sides[2] = 
    this.a = 3;
    this.b = 4;
    this.c = 5;
    this.s = (a + b + c) / 2;
    if (this.a == this.b && this.b == this.c) {
      this.angleA = 60;
      this.angleB = 60;
      this.angleC = 60;
    } else {
      this.angles = getAngles(a, b, c);
      this.angleA = angles[0] * rToD;
      this.angleB = angles[1] * rToD;
      this.angleC = angles[2] * rToD;
    }

    valid = isValid();
    this.triangleType = typeOfTriangle();
  }

  public Triangle(double a, double b, double c) {

    this.sides = longestSide(a, b, c);

    this.a = sides[0];
    this.b = sides[1];
    this.c = sides[2];
    this.s = (a + b + c) / 2;
    if (this.a == this.b && this.b == this.c) {
      this.angleA = 60;
      this.angleB = 60;
      this.angleC = 60;
    } else {
      this.angles = getAngles(a, b, c);
      this.angleA = angles[0] * rToD;
      this.angleB = angles[1] * rToD;
      this.angleC = angles[2] * rToD;
    }

    valid = isValid();
    this.triangleType = typeOfTriangle();
  }

  public Triangle(double a, double b, double angle, String location) {
    if (location.equals("a")) {
      double tempSide = sAsCalculator(a, b, angle);
      this.sides = longestSide(a, b, tempSide);
    } else if (location.equals("b")) {
      double tempAngle = sinLawAngle(b, angle, a);
      if (Double.isNaN(tempAngle)) {
        System.out.println("Your b length is not long enough to complete the triangle");
        System.exit(1);
      }
      double otherAngle = 180 - tempAngle - angle;
      double tempSide = sAsCalculator(a, b, otherAngle);
      this.sides = longestSide(a, b, tempSide);
    } else {
      double tempAngle = sinLawAngle(a, angle, b);
      if (Double.isNaN(tempAngle)) {
        System.out.println("Your a length is not long enough to complete the triangle");
        System.exit(1);
      }
      double otherAngle = 180 - tempAngle - angle;
      double tempSide = sAsCalculator(b, a, otherAngle);
      this.sides = longestSide(a, b, tempSide);
    }

    this.a = sides[0];
    this.b = sides[1];
    this.c = sides[2];
    this.s = (a + b + c) / 2;
    if (this.a == this.b && this.b == this.c) {
      this.angleA = 60;
      this.angleB = 60;
      this.angleC = 60;
    } else {
      this.angles = getAngles(a, b, c);
      this.angleA = angles[0] * rToD;
      this.angleB = angles[1] * rToD;
      this.angleC = angles[2] * rToD;
    }

    valid = isValid();
    this.triangleType = typeOfTriangle();
  }

  public Triangle(double a, double angle1, double angle2, int missing) {
    double otherAngle = 180 - angle1 - angle2;
    double side2 = sinLawSide(a, otherAngle, angle1);
    double side3 = sinLawSide(a, otherAngle, angle2);

    this.sides = longestSide(a, side2, side3);

    this.a = sides[0];
    this.b = sides[1];
    this.c = sides[2];
    this.s = (a + b + c) / 2;
    if (this.a == this.b && this.b == this.c) {
      this.angleA = 60;
      this.angleB = 60;
      this.angleC = 60;
    } else {
      this.angles = getAngles(a, b, c);
      this.angleA = angles[0] * rToD;
      this.angleB = angles[1] * rToD;
      this.angleC = angles[2] * rToD;
    }

    valid = isValid();
    this.triangleType = typeOfTriangle();
  }

  private boolean isValid() {
    boolean valid = true;
    if (angleA + angleB + angleC > 180.0000001) {
      System.out.println("The angles add up to more than 180, not a valid triangle");
      valid = false;
    }
    if (a + b < c) {
      System.out.println("The hypotenuse of the triangle is longer than the other sides combined");
      valid = false;
    }
    if (a + b == c) {
      System.out.println("The hypotenuse of the triangle is the same length as the other sides combined");
      System.out.println("this is not a triangle but a line, therefore invalid");
      valid = false;
    }
    if (valid == true) {
      return true;
    } else {
      System.exit(1);
      return false;
    }
  }

  private double sAsCalculator(double sideA, double sideB, double angle) {
    return Math.sqrt(sideA * sideA + sideB * sideB - 2 * sideA * sideB * Math.cos(angle / 180 * Math.PI));
  }

  private double sinLawSide(double aSide, double aAngle, double bAngle) {
    return Math.sin(bAngle / 180 * Math.PI) * (aSide / Math.sin(aAngle / 180 * Math.PI));
  }

  private double sinLawAngle(double aSide, double aAngle, double bSide) {
    return (Math.asin(bSide * Math.sin(aAngle / 180 * Math.PI) / aSide)) * 180 / Math.PI;
  }

  public double semiPerimeter() {
    return s;
  }

  public double area() {
    double temp = (s * (s - a) * (s - b) * (s - c));
    return Math.sqrt(temp);
  }

  public double perimeter() {
    return (a + b + c);
  }

  public double[] getAngles(double sideA, double sideB, double sideC) {
    double[] angles = new double[3];
    // returns angles from left to right, or I suppose bottom left, top, then right
    // assumes that bottom is c, left is a, and right is b
    angles[2] = Math.acos((sideB * sideB + sideA * sideA - sideC * sideC) / (2 * sideB * sideA));
    angles[1] = Math.acos((sideA * sideA + sideC * sideC - sideB * sideB) / (2 * sideA * sideC));
    angles[0] = Math.acos((sideB * sideB + sideC * sideC - sideA * sideA) / (2 * sideB * sideC));
    return angles;
  }

  public double[] longestSide() {
    double[] returns = new double[3];
    double biggest;
    double newA;
    double newB;
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

  public double[] longestSide(double sideA, double sideB, double sideC) {
    double[] returns = new double[3];
    double biggest;
    double newA;
    double newB;
    if (sideA > sideB && sideA > sideC) {
      if (sideB < sideC) {
        returns[0] = sideB; returns[1] = sideC; returns[2] = sideA;
      } else {
        returns[0] = sideC; returns[1] = sideB; returns[2] = sideA;
      }

    } else if (sideB > sideA && sideB > sideC) {
      if (sideA < sideC) {
        returns[0] = sideA; returns[1] = sideC; returns[2] = sideB;
      } else {
        returns[0] = sideC; returns[1] = sideA; returns[2] = sideB;
      }

    } else {
      if (sideA < sideB) {
        returns[0] = sideA; returns[1] = sideB; returns[2] = sideC;
      } else {
        returns[0] = sideB; returns[1] = sideA; returns[2] = sideC;
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
      double biggest;
      double newA;
      double newB;
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

  // default returns the height of a as base
  public double height() {
    double temp = (s * (s - a) * (s - b) * (s - c));
    temp = Math.sqrt(temp);
    return (temp * 2 / a);
  }

  // returns the first input as base
  public double height(double sideA, double sideB, double sideC) {
    double temp = (s * (s - sideA) * (s - sideB) * (s - sideC));
    temp = Math.sqrt(temp);
    return (temp * 2 / sideA);
  }

  // default returns the median of a
  public double median() {
    return Math.sqrt((2 * b * b + 2 * c * c - a * a) / 4);
  }

  // returns the median of the first side you put in
  public double median(double sideA, double sideB, double sideC) {
    return Math.sqrt((2 * sideB * sideB + 2 * sideC * sideC - sideA * sideA) / 4);
  }

  private double inradius() {
    return area() / s;
  }

  private double circumradius() {
    return a / (2 * Math.sin(angleC / rToD));
  }

  private double[] innerCircleCenter(double bottom, double angle1, double angle2) {
    double[] returns = new double[2];
    double angleC = 180 - angle1 - angle2;
    // takes half the angles of A and B and the side c and finds the vertex of that triangle
    double leftSide = bottom * Math.sin(angle1 / rToD) / Math.sin(angleC / rToD);
    double rightSide = bottom * Math.sin(angle2 / rToD) / Math.sin(angleC / rToD);
  
    double area = (leftSide * rightSide * Math.sin(angleC / rToD)) / 2;
    double height = 2 * area / bottom;
  
    double dis = height / Math.tan(angle1 / rToD);
    returns[0] = dis;
    returns[1] = height;
    return returns;
  }

  private double[] collisionFinder(double pointAX, double pointAY, double mA, double pointBX, double pointBY, double mB) {
    double[] collisionPoint = new double[2];
    double bA = pointAY - mA * pointAX;
    double bB = pointBY - mB * pointBX;
    double x;
    double y;

    if (mA == Math.abs(Double.POSITIVE_INFINITY)) {
      x = pointAX;
    } else if (Math.abs(mB) == Double.POSITIVE_INFINITY) {
      x = pointBX;
    } else {
      x = (bB - bA) / (mA - mB);
    }
    collisionPoint[0] = x;

    if (Math.abs(mA) == Double.POSITIVE_INFINITY) {
      y = mB * x + bB;
    } else {
      y = mA * x + bA;
    }
    collisionPoint[1] = y;

    return collisionPoint;
    
  }

  // finds the middle of the circumstribed circle. Made super easy because the second line is flat
  // this gives us the x coord instantly so all we need is an equation for the other line to find y
  public double[] outerCircleCenter(double aX, double aY, double b2X) {
    double[] returns = new double[2];
    
    // gets the midpoint of line 1
    double midAX = (0 + aX) / 2;
    double midAY = (0 + aY) / 2;

    // line 2 is flat so the midpoint is just the length / 2
    double x = b2X / 2;

    // gets the perpendicular slope at the midpoint of line 1, line two is flat so the perp is infinite
    double mA = (aX) / (aY) * -1;

    return collisionFinder(midAX, midAY, mA, x, 0, Double.POSITIVE_INFINITY);
  
  }

  public double[] orthocenter(double x, double y, double hypo) {
    double[] returns = new double[2];
    double m = (x) / (y) * -1;
    double b = -1 * m * hypo;

    double coordX = x;
    double coordY = m * coordX + b;

    returns[0] = coordX;
    returns[1] = coordY;

    return returns;
  }

  public double altitude(double side) {
    return 2 * area() / side;
  }

  private double[] changeFinder(double x1, double y1, double angle1, double x2, double y2, double angle2, boolean divide) { 
    double[] change = new double[2];
    double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    double smallAngle1 = angle1 / 3;
    double smallAngle2 = angle2 / 3;
    double otherAngle = 180 - smallAngle1 - smallAngle2;

    double lengthToVertex = distance * Math.sin(smallAngle2 / 180 * Math.PI) / Math.sin(otherAngle / 180 * Math.PI);
    // double lengthToVertexFrom2 = distance * Math.sin(smallAngle1 / rToD) / Math.sin(otherAngle / rToD);

    if (divide == true) {
      change[0] = lengthToVertex * Math.cos(smallAngle1 * 2 / 180 * Math.PI);
      change[1] = lengthToVertex * Math.sin(smallAngle1 * 2 / 180 * Math.PI);
    } else {
      change[0] = lengthToVertex * Math.cos(smallAngle1 / 180 * Math.PI);
      change[1] = lengthToVertex * Math.sin(smallAngle1 / 180 * Math.PI);
    }

    return change;
  }

  public double[] morleysTrisector(double ax, double ay, double aAngle, double bx, double by, double bAngle, double cx, double cy, double cAngle) {
    double[] vertecies = new double[6];

    double[] changes = changeFinder(ax, ay, aAngle, bx, by, bAngle, true);

    vertecies[0] = ax + changes[0];
    vertecies[1] = ay + changes[1];

    changes = changeFinder(bx, by, cAngle, cx, cy, bAngle, true);
    vertecies[2] = cx - changes[0];
    vertecies[3] = cy + changes[1];

    changes = changeFinder(ax, ay, aAngle, cx, cy, cAngle, false);
    vertecies[4] = ax + changes[0];
    vertecies[5] = ay + changes[1];

    return vertecies;
  }

  // removes all trailing zeros
  public static String fmt(double d) {
    if(d == (long) d) {
      return Long.toString((long)d);
    } else {
      return Double.toString(d);
    }
  }

  public void printTriangle() {
    // takes points A as (0, 0)
    // and B as vertex (?, bases height)
    double aShort = a;
    double bShort = b;
    double hypo = c;
    double h = aShort;
    double x = 0;
    System.out.println("\n");

    //////////////////////// RIGHT ANGLE PRINTER //////////////////////////
    if (triangleType.equals("right angle")) {

      // for each in height, which is side a, +2 to fit B and the &'s
      for (int i = 0; i < aShort + 2; i++) {
        // for the top
        if (i == 0) {
          System.out.println("  B\n  &");

          // for the bottom
        } else if (i == aShort + 1) {
          System.out.print("C &");

          // prints all of the underscores, length of b times
          for (int j = 0; j < bShort; j++) {
            if (j == Math.floor(bShort / 2 + 0.5)) {
              System.out.print("b_");
            }
            System.out.print("__");
          }
          // put A and the & on the end of the line
          System.out.println("& A");

        } else {
          // prints the left side of the triangle
          if (i == Math.floor(aShort / 2 + 0.5)) {
            System.out.print("  a");
          } else {
            System.out.print("  |");
          }
          
          // how much to indent each part of the c line
          double totalDelayAmount = b * 2;
          int howMuchDelayEachTime = (int)Math.floor(totalDelayAmount / a + 0.5);

          // prints the spaces based on the delay
          for (int delay = 0; delay < (howMuchDelayEachTime * i) - 2; delay++) {
            System.out.print(" ");
          }
          // when to print the letter c, otherwise print the backslash
          if (i == Math.floor(a / 2 + 0.5)) {
            System.out.println("c");
          } else {
            System.out.println("\\");
          }
        }
      }

      ////////////////////// EVERYTHING ELSE PRINTER ///////////////////////
    } else {
      // gets the height and the distance along line the hypotenuse to the base of the height
      h = height(hypo, aShort, bShort);
      x = Math.sqrt((aShort * aShort) - (h * h));
      int height = (int)h;
      // gets the angles of the triangle
      double timesIndented;
      double increment;

      // for each line in the height of the triangle
      for (int i = 0; i <= height; i++) {
        timesIndented = 0;
        if (i == 0) {
          for (int j = 0; j < x; j++) {
            System.out.print("  ");
          }
          System.out.println("  C");

          for (int j = 0; j < x; j++) {
            System.out.print("  ");
          }
          if (0 == height) {
            System.out.println("b & a");
          } else {
            System.out.println("  &");
          }

        } 
        if (i == height) {
          if (height == 0) {
            System.out.print("A &");
            for (int j = 0; j < hypo; j++) {
              if (j == Math.floor(x)) {
                System.out.print(" c ");
              } else {
                System.out.print("__");
              }
            }
            System.out.println("& A");
          } else {
            System.out.print("B &");
            for (int j = 0; j < hypo; j++) {
              if (j == Math.floor(x)) {
                System.out.print("_c");
              } else {
                System.out.print("__");
              }
            }
            System.out.println("& A");
          }

        } else {
          // first half of the trianlge sides printing
          // determines how much further each symbol must go
          increment = x / h;

          // prints spaces x (the distance to point C) - i * increment
          for (int j = 0; j < (x - i * increment) * 2; j++) {
            System.out.print(" ");
            timesIndented++;
          }

          // to be changed, will have different symbols depending on the angle
          // System.out.print("~");
          if (i == Math.floor(height / 2 + 0.5)) {
            System.out.print("a");
          } else if (angleB < 35) {
            System.out.print("~");
          } else if (angleB > 75) {
            System.out.print("|");
          } else {
            System.out.print("/");
          }
          timesIndented++;


          // prints a space i times increment old increment * 2 times this is to set the base indentation to x
          for (int j = 0; j < (i * increment) * 2; j++) {
            System.out.print(" ");
          }
          if (i == 1) {
            System.out.print(" "); // just to push the second slash out 1 more, lazy fix probably going to cause problems later.
          } else if (i == 0) {
            System.out.print("  ");
          }
          // for test purposes
          if (i == Math.floor(height / 2 + 0.5)) {
            System.out.print("h");
          } else {
            System.out.print("."); // shows where this is moving the indentation to
          }

          // gets new increment, this time its the distance from point C to B
          double increment2 = (hypo - x) / h;
          for (int j = 0; j < increment2 * i * 2; j++) {
            System.out.print(" ");
          }

          // will eventually print different symbols depending on the angle
          if (i == Math.floor(height / 2 + 0.5)) {
            System.out.println("b");
          } else if (angleA < 35) {
            System.out.println("~");
          } else if (angleA > 75) {
            System.out.println("|");
          } else {
            System.out.println("\\");
          }
        }

      }
    }
    // prints the information of the triangle
    double[] innerCircCoord = innerCircleCenter(hypo, angleB / 2, angleA / 2);
    double[] outerCircCoord = outerCircleCenter(x, h, hypo);
    double[] orthocenter = orthocenter(x, h, hypo);
    double[] morleysSideCoords = morleysTrisector(0, 0, angleB, x, h, angleC, hypo, 0, angleA);
    double morleySideLen = Math.sqrt(Math.pow(morleysSideCoords[0] - morleysSideCoords[2], 2) + Math.pow(morleysSideCoords[1] - morleysSideCoords[3], 2));
    double morleyArea = morleySideLen * morleySideLen / 2;
    System.out.printf("\nSide a: %s, side b: %s, side c: %s\n", fmt(aShort), fmt(bShort), fmt(hypo));
    System.out.printf("Angle A: %s*, angle B: %s*, angle C: %s*\n", fmt(angleA), fmt(angleB), fmt(angleC));
    System.out.printf("Semiperimeter: %s, area: %s, perimeter: %s\n", fmt(s), fmt(area()), fmt(perimeter()));
    System.out.printf("Height a: %s, height b: %s, height c: %s\n", fmt(height()), fmt(height(bShort, aShort, hypo)), fmt(height(hypo, bShort, aShort)));
    System.out.printf("Median a: %s, median b: %s, median c: %s\n", fmt(median()), fmt(median(bShort, aShort, hypo)), fmt(median(hypo, bShort, aShort)));
    System.out.printf("Inradius: %s, circumradius: %s\n", fmt(inradius()), fmt(circumradius()));
    if (triangleType.equals("right angle")) {
      System.out.printf("Coords of C: (0, 0), of B: (%s, 0), of A: (%s, 0)\n", fmt(x), fmt(h), fmt(hypo));
    } else {
      System.out.printf("Coords of B: (0, 0), of C: (%s, %s), of A: (%s, 0)\n", fmt(x), fmt(h), fmt(hypo));
    }
    System.out.printf("Centroid: (%s, %s), inner circle center: (%s, %s), outer circle center: (%s, %s)\n", fmt((x + hypo) / 3), fmt(h / 3), fmt(innerCircCoord[0]), fmt(innerCircCoord[1]), fmt(outerCircCoord[0]), fmt(outerCircCoord[1]));
    System.out.printf("Orthocenter: (%s, %s)\n", fmt(orthocenter[0]), fmt(orthocenter[1]));
    System.out.printf("Altitude a: %s, b: %s, c: %s\n", fmt(altitude(aShort)), fmt(altitude(bShort)), fmt(altitude(hypo)));
    System.out.printf("Morley's triangle vertecies: D: (%s, %s), E: (%s, %s), F: (%s, %s)\n", fmt(morleysSideCoords[0]), fmt(morleysSideCoords[1]), fmt(morleysSideCoords[4]), fmt(morleysSideCoords[5]), fmt(morleysSideCoords[2]), fmt(morleysSideCoords[3]));
    System.out.printf("Morley's triangle side lengths: %s\n", fmt(morleySideLen));
    System.out.printf("Morley's triangle area: %s, perimeter: %s, semiperimeter: %s\n", fmt(morleyArea), fmt(morleySideLen * 3), fmt(morleySideLen * 1.5));
  }
}
