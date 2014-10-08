package edu.grinnell.csc207.roylewil16.layout;

import java.util.Arrays;

/**
 * A grid consisting of one character, with the grid having a specified
 * height and width. The grid will be at least 1 X 1 in size, but may
 * be bigger
 * 
 * @author William Royle
 * @date 27 September 2014
 */
public class Grid
    implements TextBlock
{

  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The height of the grid.
   */
  int height;

  /**
   * The width of the grid
   */
  int width;

  /**
   * The character the grid is composed of
   */
  char theChar;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new grid of the specified width and height, composed of the 
   * character content.
   * @pre width, height > 0
   * @exception Exception
   *              if width, height <= 0
   */
  Grid(int width, int height, char content) throws Exception
  {
    if (height <= 0)
      {
        throw new Exception("Must give a positive height");
      }// if
    if (width <= 0)
      {
        throw new Exception("Must give a positive width");
      }// if
    // Save height, with and character
    this.height = height;
    this.width = width;
    theChar = content;
  }// Grid Constructor

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get one row from the block.
   * 
   * @pre i > 0 , i < height 
   * @exception Exception
   *              if i < 0 or i >= height
   */
  public String row(int i)
    throws Exception
  {
    //If i is invalid, throw exception
    if (i < 0 || i >= height)
      {
        throw new Exception("Row " + i + " does not exist");
      }// if
    //Citation 1
    char[] characterArray = new char[width];
    Arrays.fill(characterArray, theChar);
    return new String(characterArray);

  }// row 

  /**
   * Determine the character the grid is composed of.
   */
  public char getChar()
  {
    return theChar;
  }// getChar

  /**
   * Determine how many rows are in the grid.
   */
  public int height()
  {
    return height;
  }// height

  /**
   * Determine how many columns are in the grid.
   */
  public int width()
  {
    return width;
  }// width
}// Grid

// Citation 1:
// http://stackoverflow.com/questions/1900477/can-one-initialise-a-java-string-with-a-single-repeated-character-to-a-specific