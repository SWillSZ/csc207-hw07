package com.farevee.shopping;

/**
 * Methods for making and utilizing a shopping cart.
 * 
 * @author William Royle and Hattie Zucker
 * @date 4 October 2014
 */
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;

import com.farevee.groceries.Item;
import com.farevee.groceries.ManyPackages;
import com.farevee.groceries.Weight;

/**
 * A shopping cart filled with grocery items. Includes methods which allow
 * one to add and remove items from the cart, as well as combine similar 
 * items
 * 
 * @author Harriet Zucker, William Royle
 * @date October 5, 2014
 */
public class Cart
{
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The items which actually reside within the shopping cart
   */
  LinkedList<Item> items;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   *  Creates a new cart object
   */
  Cart()
  {
    items = new LinkedList<Item>();
  }// Cart Constructor

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+
  
  /**
   *  Add an item to the cart.
   */
  public boolean addItem(Item toAdd)
  {
    return items.add(toAdd);
  }// addItem

  /**
   *  Get the number of things in the cart. By number of things, each BulkItem,
   *  BulkContainer and Package counts as one, while ManyPackages depends on 
   *  the number of packages within
   */
  public int numThings()
  {
    // initialize the total (which will be the number of things) as the size of
    // items
    int total = items.size();
    // loop through to check for ManyPackages
    for (int count = 0; count < items.size(); count++)
      {
        Item element = items.get(count);
        if (element instanceof ManyPackages)
          {
            // add the count of ManyPackages to the total
            total += ((ManyPackages) element).getCount();
            // then decrement by one, as we do not want to double count
            total -= 1;
          }// if
      }// for
    return total;
  }// numThings

  /**
   *  Get the number of entries in the cart.
   */
  public int numEntries()
  {
    return items.size();
  }// numEntries

  /**
   *  Print the contents of the cart. The output has a total of one item per
   *  line
   */
  public void printContents(PrintWriter pen)
  {
    // print header
    pen.println("Your shopping cart contains:");
    // loop through the whole cart
    for (int count = 0; count < items.size(); count++)
      {
        // print each item
        pen.println(items.get(count).toString());
      }// for
  }// printContents

  /**
   *  Computes the total price of the cart, in cents. 
   */
  public int getPrice()
  {
    int totalPrice = 0;
    // loop through to add each price
    for (int count = 0; count < items.size(); count++)
      {
        totalPrice += items.get(count).getPrice();
      }// for
    return totalPrice;
  }// getPrice

  /**
   * Returns the weight of everything in the cart, as an array of Weight Objects
   * each weight object in the returned array will have different units
   * @post if i!=j, getWeight()[i].sameUnit()[j]==false
   */
  public Weight[] getWeight()
  {
    //We create a copy of the existing weights
    LinkedList<Weight> originalWeights = new LinkedList<Weight>();
    for (int count = 0; count < items.size(); count++)
      {
        originalWeights.add(items.get(count).getWeight());
      }// for
    // We initialize the array we will return to the max possible sixe
    Weight[] weightList = new Weight[items.size()];
    // iterator goes through each element in originalWeights
    int iterator = 0;
    while (!originalWeights.isEmpty())
      {
        // we get the next element in our list of weights
        weightList[iterator] = originalWeights.getFirst();
        originalWeights.remove();
        // we loop through the weights, adding all like weights to weightlist
        for (int count = 0; count < originalWeights.size(); count++)
          {
            if (weightList[iterator].sameUnit(originalWeights.get(count)))
              {
                // sumWeight will throw an Exception if weightList[iterator] and
                // originalWeights.get(count) use different units, but we have
                // already checked that case, thus we do not need any particular
                // exception handling in this case
                try
                  {
                    // we add the newly found weight to weightList[iterator]
                    weightList[iterator] =
                        weightList[iterator].sumWeight(originalWeights.get(count));
                  }
                catch (Exception E)
                  {
                  }
                // we remove the newly found weight
                originalWeights.remove(count);
                count--;
              }// if 
          }// for
        iterator++;
      }// while
    // we trim out away before returning it
    return Arrays.copyOf(weightList, iterator);
  }// getWeight

  /**
   * Removes all items from the cart with the specified name
   * @post for all count, items.get(count).getName().equals(name)==false
   */
  public void remove(String name)
  {
    // we loop through array
    for (int count = 0; count < items.size(); count++)
      {
        // if the item has the specified name, we delete the item
        if (items.get(count).getName().equals(name))
          {
            items.remove(count);
            count--;
          }// if
      }// for

  }// remove

  /**
   * Combines all similar elements within our items linkedlist. Two items
   * qualify as similar if item1.sameType(item2)==true. For instance, if we
   * have two packages each with 2 oz. of bananas, we will now have one
   * manyPackages with a total of two packages of bananas, each with 2 oz. in
   * weight. Items are merged, no items are arbitrarily added or deleted
   * @post for all i, j, we now have items.get(i).sameType(items.get(j))==false
   */
  public void merge()
    throws Exception
  {
    // we loop through the linked list
    for (int count = 0; count < items.size(); count++)
      {
        // we loop through the rest of the linked list
        for (int count2 = count + 1; count2 < items.size(); count2++)
          {
            // we combine the two items if we can. If we can, then we do and 
            Item first=items.get(count);
            Item second=items.get(count2);
            Item combine=first.combine(second);
            if (combine!=null)
              {
                // we clean up the merged elements, adding in the newly created element
                items.remove(count);
                items.add(count, combine);
                items.remove(count2);
                // we then decrement count2 to avoid skipping over checking an element
                count2--;
              }
          }// for
      }// for
  }// merge

  /**
   * Combines the two items at position first, other if possible. It inserts the item
   * formed by combining item first and item other at the old position of item first, 
   * thendeleting the origal item first and item other. The length of the linked list
   * will thus be decremented by one
   */

}// Cart
