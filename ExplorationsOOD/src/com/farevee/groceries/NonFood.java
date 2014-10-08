package com.farevee.groceries;

/**
 * An item which is not a food
 * 
 * Ex. can opener
 * 
 * @author Harriet Zucker, William Royle
 * @date September 28, 2014
 */
public class NonFood
    implements Item
{

  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The weight of the item
   */
  Weight weight;

  /**
   * The name of the item
   */
  String name;

  /**
   * The price of the item (in cents)
   */
  int price;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Instantiate a new non-food object with the parameters passed as 
   * properties
   */
  public NonFood(String name, Weight weight, int price)
  {
    this.weight = weight;
    this.name = name;
    this.price = price;
  }// NonFood Constructor

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  
  /**
   * Returns the name of the item.
   */
  public String toString()
  {
    return name;
  }// toString

  /**
   * Returns the weight of the item.
   */
  public Weight getWeight()
  {
    return weight;
  }// getWeight

  /**
   * Returns the cost of the item, in cents
   */
  public int getPrice()
  {
    return price;
  }// getPrice

  public String getName()
  {
    return name;
  }// getName
  
  
  /**
   * equals will return true if this is identical to other, ie if 
   * they have the same names, prices and weights
   */
  public boolean equals(NonFood other)
  {
    return (other.name.equals(this.name) && other.weight.equals(this.weight) && other.price == this.price);
  }// equals

  
  /**
  * Will return the object formed by merging this with other. In this case,
  * this is impossible, as NonFood objects cannot be merged, and so 
  * we return null
  */
  public Item combine(Item other)
  {
    return null;
  }// combine

}// NonFood
