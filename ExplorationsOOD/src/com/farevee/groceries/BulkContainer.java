package com.farevee.groceries;

/**
* Make a specific container for a BulkItem.
* Extends BulkItem. The container is simply the enclosure of the bulk item,
* Ex. If we have a BulkItem of "5 pounds of tomatoes", a BulkContainer could
* be "can of 5 pounds of tomatoes"
*
* @author Harriet Zucker, William Royle
* @date 29 September 2014
*/
public class BulkContainer
    extends BulkItem
{
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+
  
  /**
  * Name of container (Ex. jar)
  */
  String container;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+
  /**
  * Create a container
  */
  public BulkContainer(String container, BulkFood food, Units unit, int amount)
      throws Exception
  {
    super(food, unit, amount);
    this.container = container;
  } // BulkContainer Constructor

  /**
  * Create a container using a weight
  */
  public BulkContainer(String container, BulkFood food, Weight weight)
  {
    super(food, weight);
    this.container = container;
  }// BulkContainer Constructor

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
  * Creates a string representation of the container
  * Ex. jar of 3 grams of tapioca
  */
  public String toString()
  {
    //append the name of the container to string representation of food
    return container + " of " + super.toString();
  }//toString()

  /**
  * Returns the weight of the BulkContainer & contents
  */
  public Weight getWeight()
  {
    return new Weight(this.unit, this.amount);
  }//getWeight()
  
  /**
  * Returns the price of the BulkContainer & contents in cents
  */
  public int getPrice()
  {
    return (this.amount * this.food.pricePerUnit);
  }//getPrice()
  
  
  /**
  * Tells whether other is identical to this. To be identical, it 
  * must have the same container, and the regula BulkItem contents must
  * also be the same
  */
  @Override
  public boolean equals(Item other)
  {
    //We cast other to BulkContainer
    if (other instanceof BulkContainer)
      {
        BulkContainer otherBulkContainer = (BulkContainer) other;
        // They are only identical if both containter and other BulkItem
        // properties are the same
        return container.equals(otherBulkContainer.container)
               && super.equals(other);
      }// if
    return false;
  }// equals
  
  /**
  * Will return the object formed by merging this with other. However,
  * as BulkContainers cannot be consolidated, this method will always
  * return null
  */
  @Override
  public Item combine(Item other)
  {
    return null;
  }// combine
  
}//class BulkContainer