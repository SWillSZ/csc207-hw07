package com.farevee.groceries;

/**
 * Each object represents a weight (ie, 2 lb)
 * the amount and unit fields together form this representation
 * 
 * @author Harriet Zucker, William Royle
 * @date October 5, 2014
 */
public class Weight
{
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The units that the weight is in (i.e ounces)
   */
  Units unit;

  /**
   * The quantity of units which defines this weight
   */
  int amount;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Creates a new weight object, consisting of quantity units in terms of 
   * weight.
   */
  public Weight(Units unit, int amount)
  {
    this.unit = unit;
    this.amount = amount;
  }// Weight Constructor

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Tells if this is the same as other
   * Will return true if both the units and amounts parameters are the same
   * for both this and other, otherwise will return false
   */
  public boolean equals(Weight other)
  {
    // If the amounts are the same, We only need to check that
    // the name of the units are identical 
    return (other.amount == amount && unit.name.equals(other.unit.name));
  }// equals

  /**
   * Returns a summary of the weight
   */
  public String toString()
  {
    //We return plural case or regular, depending on amount
    if (amount == 1)
      {
        return Integer.toString(amount) + " " + unit.name;
      }
    else
      {
        return Integer.toString(amount) + " " + unit.plural;
      }
  }// toString

  /**
   * Returns an abbreviated summary of the weight
   */
  public String toAbbrevString()
  {
    return Integer.toString(amount) + " " + unit.abbrev;
  }// toAbbrevString

  /**
   * Returns true if both this and other use the same
   * units
   */
  public boolean sameUnit(Weight other)
  {
    // We only need to check that the name of the units is 
    // identical
    return other.unit.name.equals(unit.name);
  }

  /**
   * Returns true if this uses the parameter as it's units
   */
  public boolean sameUnit(Units unit)
  {
    return this.unit.name.equals(unit.name);
  }

  /**
   * This will return this combined with other
   * @pre increase.unit.equals(unit)
   * @exception Exception
   *              if the precondition is not met
   */
  public Weight sumWeight(Weight increase)
    throws Exception
  {
    if (!this.sameUnit(increase))
      {
        throw new Exception("increaseWeight: Cannot add two weights with differing units");
      }// if
    return new Weight(unit, this.amount + increase.amount);
  }// sumWeight

}// Weight
