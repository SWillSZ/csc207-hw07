package com.farevee.groceries;

/**
 * Represents an item, a defined quantity of a physical item which can be 
 * bought by a consumer
 * 
 * Ex. jar of 3 grams of tapioca
 * Ex. can opener
 * 
 * @author Harriet Zucker, William Royle
 * @date 4 October 2014
 */
public interface Item
{
  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get the total weight of item
   */
  public Weight getWeight();

  /**
   * Get the total cost of item
   */
  public int getPrice();

  /**
   * Get a quick summary of item
   */
  public String toString();

  /**
   * Get name of the item
   */
  public String getName();

  /**
   * Will return the object formed by merging this with other. If such a merge
   * is not possible, this method will return null
   */
  public Item combine(Item other);


}// interface Item
