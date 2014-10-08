package edu.grinnell.csc207.roylewil16.layout;

/**
 * Is a TextBlock truncated to one specific width. Both the TextBlock to be
 * truncated and the width are passed in as parameters
 * 
 * @author William Royle
 * @date 27 September 2014
 */
public class TruncatedBlock
    implements TextBlock
{

  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The block of text that is truncated to form TruncatedBlock
   */
  TextBlock contents;

  /**
   * the width of the TruncatedBlock 
   */
  int width;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new TruncatedBlock object, composed of the tb Textblock
   * truncated so that each line has at most width characters 
   * @pre width >= 0
   * @exception Exception
   *              if width < 0
   */
  public TruncatedBlock(TextBlock contents, int width) throws Exception
  {
    if (width < 0)
      {
        throw new Exception("Must provide non-negative width");
      }// if
    this.width = width;
    this.contents = contents;
  } // TruncatedBlock Constructor

  /**
   * Determine how many columns are in the TruncatedBlock.
   */
  public int width()
  {
    return width;
  }// width

  /**
   * Determine how many rows are in the TruncatedBlock.
   */
  public int height()
  {
    return contents.height();
  }// height

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
    String toChop = contents.row(i);
    // If the width we want to truncate to is larger than the block, we just return the row
    if (width > contents.width())
      {
        return toChop;
      }
    //Otherwise, we want to shorten the row
    else
      {
        return toChop.substring(0, width);
      }
  }// row

}// TruncatedBlock
