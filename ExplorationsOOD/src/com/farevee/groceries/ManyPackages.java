package com.farevee.groceries;

/**
 * A collection of a number of identical Package objects
 * 
 * @author Harriet Zucker, William Royle
 * @date 5 October 2014
 */
public class ManyPackages
    implements Item
{

  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The type of Package
   */
  Package type;

  /**
   * The number of packages
   */
  int count;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Instantiate a new ManyPackages object with the parameters passed as 
   * properties, thus, we expect the object to be comprised of count package
   * objects
   * @pre count >=0
   * @exception Exception
   *              if the precondition is not met
   */
  public ManyPackages(Package type, int count) throws Exception
  {
    if (count < 0)
      {
        throw new Exception("A non-negative number of packages is expected");
      }
    this.type = type;
    this.count = count;
  }// ManyPackages Constructor

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Returns a summary of the collection of packages
   */
  public String toString()
  {
    return Integer.toString(count) + " x " + type.toString();
  }// toString

  /**
   * Returns the total combined weight of all of the packages.
   */
  public Weight getWeight()
  {
    return new Weight(type.weight.unit, type.weight.amount * count);
  }// getWeight

  /**
   * Returns the total cost of ManyPackages, in cents
   */
  public int getPrice()
  {
    return type.price * count;
  }// getPrice

  
  /**
   * returns the number of packages in this ManyPackages object
   */
  public int getCount()
  {
    return count;
  }// getCount

  /**
   * returns the package that is inside this ManyPackages object
   */
  public Package getPackage()
  {
    return type;
  }// getPackage

  /**
   * returns the name of each of the packages
   */
  public String getName()
  {
    return type.name;
  }// getName
  /**
   * equals will return true if this and other point to the same memory
   * location. Otherwise, equals will return false;
   */
  public boolean equals(ManyPackages other)
  {
    return other == this;
  }// equals


  /**
  * Will return the object formed by merging this with other. In this case,
  * this is possible when other is a Package or ManyPackages, as long as they
  * have identical underlying Packages
  * if merging is not possible, null will be returned
  */
  public Item combine(Item other)
  {
    if (other instanceof Package)
      {
        
        Package otherPackage = (Package) other;
        if (type.equals(otherPackage))
          {
            // As count>0, an exception is logically impossible, but we
            // handle it appropriately anyway by returning null
            try
              {
                return new ManyPackages(otherPackage, count + 1);
              }// try
            catch (Exception E)
              {
              }// catch
          }// if
      }
    if (other instanceof ManyPackages)
      {
        ManyPackages otherManyPackages = (ManyPackages) other;
        if (type.equals(otherManyPackages.type))
          {
            // As count>0, an exception is logically impossible, but we
            // handle it appropriately anyway by returning null
            try
              {
                // We add the counts of both ManyPackages objects
                return new ManyPackages(type, count + otherManyPackages.getCount());
              }
            catch (Exception E)
              {
              }// catch
          }// if
      }// if
    return null;
  }// combine

}// ManyPackages
