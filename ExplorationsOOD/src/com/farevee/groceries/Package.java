package com.farevee.groceries;

/**
 * A single parcel of a food item
 * 
 * @author Harriet Zucker, William Royle
 * @date October 5, 2014
 */
public class Package
    implements Item
{

  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The weight of the package
   */
  Weight weight;

  /**
   * The name of the package
   */
  String name;

  /**
   * The price of the package (in cents)
   */
  int price;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Instantiate a new package object with the parameters passed as 
   * properties
   */
  public Package(String name, Weight weight, int price)
  {
    this.weight = weight;
    this.name = name;
    this.price = price;
  }// Package Constructor

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Returns a summary of the package
   */
  public String toString()
  {
    return weight.toAbbrevString() + " package of " + name;
  }// toString

  /**
   * Returns the weight of the package.
   */
  public Weight getWeight()
  {
    return weight;
  }// getWeight

  /**
   * Returns the cost of the package, in cents
   */
  public int getPrice()
  {
    return price;
  }// getPrice

  /**
   * Returns the name of the package
   */
  public String getName()
  {
    return name;
  }// getName

  /**
   * equals will return true if this is identical to other, ie if 
   * they have the same names, prices and weights
   */
  public boolean equals(Package other)
  {
    // We check to make sure the name, weight and price are identical
    return (other.name.equals(this.name) && other.weight.equals(this.weight) && other.price == this.price);
  }// equals

  /**
   * Will return true if this and other are the same type. If they are the
   * same type, then the two items can be consolidated into a larger item.
   * 
   */
  public boolean sameType(Item other)
  {
    //We check if other is a package
    if (other instanceof Package)
      {
        Package otherPackage = (Package) other;
        return otherPackage.equals(this);
      }// if
    // otherwise, if other is a manyPackage
    if (other instanceof ManyPackages)
      {
        ManyPackages otherManyPackages = (ManyPackages) other;
        return otherManyPackages.type.equals(this);
      }// if
    // If neither, we simply return false
    return false;
  }// sameType
  
  
  /**
  * Will return the object formed by merging this with other. In this case,
  * other can be either a Package Object or a ManyPackage Object and we can
  * still return a ManyPackages.
  * @post if combine(other)!=null
  *     (combine instanceof ManyPackages)==true
  */
  public Item combine(Item other)
  {
    // if other is a package
    if (other instanceof Package)
      {
        Package otherPackage = (Package) other;
        if (otherPackage.equals(this))
          {
            // As 2>0, an exception is logically impossible, but we
            // handle it appropriately anyway by returning null
            try
              {
                return new ManyPackages(this, 2);
              }// try
            catch (Exception e)
              {
              }// catch
          }
      }// if
    // otherwise, if other is a manyPackage
    if (other instanceof ManyPackages)
      {
        ManyPackages otherManyPackages = (ManyPackages) other;
        if (otherManyPackages.type.equals(this))
          {
            // As 1,otherManyPackages.count>0, an exception is logically impossible, 
            // but we handle it appropriately anyway by returning null
            try
            {
            return new ManyPackages(this, otherManyPackages.getCount() + 1);
            }// try
            catch (Exception e)
            {
            }// catch
          }
      }// if
    return null;
  }// combine

}// Package
