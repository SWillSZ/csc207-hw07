package edu.grinnell.csc207.roylewil16.layout;

import java.io.PrintWriter;

/**
 * A series of experiments with the text block layout classes.
 * 
 * @author Samuel A. Rebelsky
 * @version 1.2 of September 2014
 */
public class TBExpt
{
  // +------+--------------------------------------------------------------
  // | Main |
  // +------+

  public static void main(String[] args)
    throws Exception
  {
    // Prepare for input and output
    PrintWriter pen = new PrintWriter(System.out, true);
    
    
    
    TextLine tb1 = new TextLine("Hello");
    TextLine tb2 = new TextLine("World");
    TextBlock compound = new BoxedBlock(new CenteredBlock(new BoxedBlock(
        new CenteredBlock(new VComposition(tb1, tb2), 7)), 15));
    
    TBUtils.print(pen,new BoxedBlock(new RightJustified(compound,50)));

    // Create a block to use

    
    // Print out the block
    
    // Clean up after ourselves.
    pen.close();
  } // main(String[])

} // class TBExpt
