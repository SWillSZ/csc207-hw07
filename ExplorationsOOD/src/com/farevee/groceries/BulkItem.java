package com.farevee.groceries;

/**
 * Represents a quantity of a BulkFood. For example, if a tomato bulk food 
 * item was 2 dollars a pound, a bulk item could be 2 pounds of tomatoes
 * 
 * @author Harriet Zucker, William Royle
 * @date 5 October 2014
 */
public class BulkItem
    implements Item
{

  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The BulkFood which this object represents a quantity of
   */
  BulkFood food;

  /**
   * The units which are used to quantify our BulkItem
   */
  Units unit;

  /**
   * number of BulkFoods in this BulkItem, in terms of units. 
   */
  int amount;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Instantiate a new BulkIood object with the specified parameters
   */
  public BulkItem(BulkFood food, Units unit, int amount) throws Exception
  {
    // We can't have a negative amount of a BulkItem
    if (amount < 0)
      {
        throw new Exception("BulkItem-Constructor: Cannot have a negative amount of a BulkItem");
      }
    this.food = food;
    this.unit = unit;
    this.amount = amount;
  }// BulkIood Constructor

  /**
   * Instantiate a new BulkIood object, this time passing in a weight 
   * instead of a unit and a amount
   */
  public BulkItem(BulkFood food, Weight weight)
  {
    this.food = food;
    this.unit = weight.unit;
    this.amount = weight.amount;
  }// BulkItem Constructor

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Returns a description of the BulkItem
   */
  public String toString()
  {
    return (new Weight(unit, amount)).toString() + " of " + food.name;
  }// toString

  /**
   * Returns the weight of the BulkItem
   */
  public Weight getWeight()
  {
    return new Weight(unit, amount);
  }// getWeight

  /**
   * Returns the total cost of the BulkItem (in cents)
   */
  public int getPrice()
  {
    return food.pricePerUnit * amount;
  }// getPrice

  /**
   * Returns food which this BulkItem is build from
   */
  public BulkFood getFood()
  {
    return food;
  }// getFood

  /**
   * Returns the name of the BulkItem
   */
  public String getName()
  {
    return food.name;
  }// getName

  /**
   * Tells if this object is equal to other (same fields, type)
   */
  public boolean equals(Item other)
  {
    if (other instanceof BulkItem)
      {
        //Cast to BulkItem
        BulkItem otherBulkItem = (BulkItem) other;
        // Make sure both the underlying BulkFood and units and amount are the same
        return otherBulkItem.getFood().sameType(food)
               && otherBulkItem.getWeight().equals(new Weight(unit, amount));
      }// if
    return false;
  }// equals

  /**
   * Returns whether this is similar to other. If it is similar, then
   * the only allowed differentiating factor between the two BulkItems
   * is the amount
   */
  public boolean sameType(Item other)
  {
    if (other instanceof BulkItem)
      {
        BulkItem otherBulkItem = (BulkItem) other;
        // They must have the same underlying BulkFood and the same units
        return otherBulkItem.getFood().sameType(food)
               && getWeight().sameUnit(otherBulkItem.getWeight());
      }// if
    return false;
  }// sameType

  
  /**
  * Will return the object formed by merging this with other. In this case,
  * this is only possible if other is a BulkItem with the same underlying
  * food type, in which case we add the amounts
  * @post if combine(other)!=null
  *     combine(other).getWeight().amount==getWeight().amount
  *     +other.getWeight().amount
  */
  public Item combine(Item other)
  {
    if (other instanceof BulkItem)
      {
        BulkItem otherBulkItem = (BulkItem) other;
        // If this and other are the same type of bulk food
        if (otherBulkItem.getFood().sameType(food)
            && getWeight().sameUnit(otherBulkItem.getWeight()))
          {
            // We handle the impossible new BulkItem exception (both bulkItems will have)
            // positive weight, and thus combining them will not result in a negative weight
            try
              {
                // Sum the weights up and return the result
                return new BulkItem(
                                    getFood(),
                                    getWeight().sumWeight(otherBulkItem.getWeight()));
              }// try
            catch (Exception E)
              {
                return null;
              }// catch

          }// if
      }// if
    return null;
  }// combine
}// Package
