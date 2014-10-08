package com.farevee.shopping;

import java.io.PrintWriter;

import com.farevee.groceries.*;
import com.farevee.groceries.Package;

public class Testing
{
  public static void main(String[] args)
    throws Exception
  {
    PrintWriter out = new PrintWriter(System.out, true);
    Cart newCart = new Cart();
    // The store has 20 pounds of bananas, priced at 50 cents per pound
    BulkFood bananas = new BulkFood("bananas", Units.POUND, 50, 20);
    // The store has 200 grams of saffron, priced at 1000 cents per gram
    BulkFood saffron = new BulkFood("saffron", Units.GRAM, 1000, 200);

    // The customer adds three pounds of bananas to the cart
    newCart.addItem(new BulkItem(bananas, Units.POUND, 3));

    // The customer adds a jar of 3 grams of saffron to the cart
    newCart.addItem(new BulkContainer("jar", saffron, Units.GRAM, 3));

    // The customer adds a bag of 1 gram of saffron to the cart
    newCart.addItem(new BulkItem(saffron, Units.GRAM, 1));
    
    
    // The customer adds three pounds of bananas to the cart
    newCart.addItem(new BulkItem(bananas, Units.POUND, 4));

    // The customer adds a can opener to the cart, priced $3.489.
    newCart.addItem(new NonFood("can opener", new Weight(Units.OUNCE, 2), 349));
    newCart.addItem(new ManyPackages(
                                     new Package("macncheez",
                                                 new Weight(Units.OUNCE, 6), 77),
                                     4));
    // The customer adds a box of oreos to the cart
    newCart.addItem(new Package("oreos", new Weight(Units.OUNCE, 12), 399));
    // The customer adds five 6oz packages of macncheez to the cart, each 
    // priced at 77 cents.
   /* newCart.addItem(new ManyPackages(
                                     new Package("macncheez",
                                                 new Weight(Units.OUNCE, 6), 77),
                                     4));*/
    

    newCart.addItem(new ManyPackages(
                                     new Package("macncheez",
                                                 new Weight(Units.OUNCE, 6), 77),
                                     4));
    
   newCart.addItem(new Package("macncheez",new Weight(Units.OUNCE, 6), 77));
    
   // newCart.remove("bananas");
   // newCart.remove("saffron");
    newCart.merge();
    Weight[] theWeights = newCart.getWeight();
      for (int i=0;i<theWeights.length;i++)
        {
    out.println(theWeights[i]);
        }// for
    newCart.printContents(out);
    System.out.println("Done");
  }// Main
}// Testing
