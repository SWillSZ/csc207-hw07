package edu.grinnell.csc207.roylewil16.layout;

/**
 * A collection of simple math utilities.
 */
public class MathUtils
{
  /**
   * Compute an approximate square root of num.
   * 
   * @pre num > 1.0
   * @post the square of the result is within .01 of num.
   */
  public static double squareRoot(Number num)
  {
    double n = num.doubleValue();
    double lower = 1.0;
    double upper = n;
    while (upper - lower > 0.0001)
      {
        double mid = upper / 2.0 + lower / 2.0;
        if (mid * mid < n)
          lower = mid;
        else
          upper = mid;
      } // while
    return (upper + lower) / 2.0;
  } // squareRoot(Number)
} // class MathUtils
