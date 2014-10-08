package edu.grinnell.csc207.roylewil16.layout;

/**
 * A pair of TextBlocks, arranged side by side. This object takes one 
 * textblock in the constructor, and then this object consists of the 
 * one textblock repeated twice horizontally
 * 
 * @author William Royle
 * @date 27 September 2014
 */
public class BlockPair
    implements TextBlock
{

  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The block which will be arranged side by side with itself
   */
  TextBlock contents;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new BlockPair object by repeating singleblock two times in a
   * row.
   */
  BlockPair(TextBlock contents) throws Exception
  {
    this.contents = contents;
  } // BlockPair Constructor

  /**
   * Determine how many rows are in the BlockPair object.
   */
  public int height()
  {
    return contents.height();
  }// height

  /**
   * Determine how many columns are in the BlockPair object.
   */
  public int width()
  {
    return (2 * contents.width());
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
    return contents.row(i) + contents.row(i);
  }// row
}
