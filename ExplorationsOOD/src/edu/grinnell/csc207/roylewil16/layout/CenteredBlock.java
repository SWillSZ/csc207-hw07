package edu.grinnell.csc207.roylewil16.layout;

/**
 * The centering of one TextBlock according to a given width
 * 
 * @author William Royle
 * @date 27 September 2014
 */
public class CenteredBlock
    implements TextBlock
{

  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * A block of text which is the centered text block.
   */
  TextBlock contents;

  /**
   * The width of the CenteredBlock, including padding
   */
  int width;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new CenteredBlock object, composed of the tb Textblock
   * with each line padded so that the tb Textblock is centered
   * @pre width > tb.width()
   * @exception Exception
   *              if width < tb.width()
   */
  CenteredBlock(TextBlock contents, int width) throws Exception
  {
    if (width < contents.width())
      {
        throw new Exception(
                            "A width which was less than table width was passed");
      }// if
    this.contents = contents;
    this.width = width;

  }// Centered Block Constructor

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Determine how many rows are in the CenteredBlock object.
   */
  public int height()
  {
    return contents.height();
  }// height

  /**
   * Determine how many columns are in the CenteredBlock object.
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
    // We count spaces to place on either side
    int spareSpaces = width - contents.width();
    int spacesLeft = spareSpaces / 2;
    int spacesRight = spacesLeft + (spareSpaces % 2);
    return new String(TBUtils.spaces(spacesLeft) + contents.row(i)
                      + TBUtils.spaces(spacesRight));
  }// row
}// CenteredBlock
