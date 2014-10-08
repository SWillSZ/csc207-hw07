package edu.grinnell.csc207.roylewil16.layout;


/**
 * A right justified TextBox.
 * 
 * @author William Royle
 * @date 27 September 2014
 */
public class RightJustified
    implements TextBlock
{

  
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+
  
  /**
   * A block of text which is the right justified text block.
   */
  TextBlock contents;
  
  
  /**
   * The width of the RightJustified TextBlock, including padding
   */
  int width;

  
  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+
  
  /**
   * Build a new RightJustified object, composed of the tb Textblock
   * with each line padded so that the tb Textblock is right justified
   * @pre width >= tb.width()
   * @exception Exception
   *              if width < tb.width()
   */
  RightJustified(TextBlock contents, int width) throws Exception
  {
    if (width < contents.width())
      {
        throw new Exception("A width which was less than the table width was passed");
      }// if
    this.contents = contents;
    this.width = width;
  }// RightJustified Constructor

  /**
   * Determine how many rows are in the RightJustified object.
   */
  public int height()
  {
    return contents.height();
  }// height

  /**
   * Determine how many columns are in the RightJustified object.
   */
  public int width()
  {
    return width;
  }// width

  
  
  /**
   * Get one row from the block.
   * 
   * @pre 0 <= i < this.height()
   * @exception Exception
   *              if the precondition is not met
   */
  public String row(int i)
    throws Exception
  {
    // We count spaces to place on the left
    int spacesLeft = width - contents.width();
    return new String(TBUtils.spaces(spacesLeft) + contents.row(i));
  }// row

}// RightJustified
