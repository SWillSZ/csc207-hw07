package com.farevee.groceries;

/**
 * Information and inventory on a type of food kept by store
 * 
 * @author Harriet Zucker, William Royle
 * @date 29 September 2014
 */
public class BulkFood
{

  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The name of the bulk food
   */
  String name;

  /**
   * The units which are used to inventory the bulk food
   */
  Units unit;

  /**
   * Cost per unit
   */
  int pricePerUnit;

  /**
   * Units of supply
   */
  int supply;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Instantiate a new BulkFood object with the specified parameters
   */
  public BulkFood(String name, Units unit, int pricePerUnit, int supply)
  {
    //We simply pass the parameters to the fields
    this.name = name;
    this.unit = unit;
    this.pricePerUnit = pricePerUnit;
    this.supply = supply;
  }// BulkFood Constructor

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
  * Tells whether other is of the same type as this. To be of the sameType, 
  * we must have the same name, unit, pricePerUnit. Supply does not matter, 
  * as we are checking the type, not if the two are identical
  */
  public boolean sameType(Object other)
  {
    //We cast other to BulkContainer
    if (other instanceof BulkFood)
      {
        BulkFood otherBulkFood = (BulkFood) other;
        // They are only identical if both containter and other BulkItem
        // properties are the same
        return name.equals(otherBulkFood.name)
               && pricePerUnit == otherBulkFood.pricePerUnit
               && unit.name.equals(otherBulkFood.unit.name);
      }// if
    return false;
  }// sameType
  

}// Package
