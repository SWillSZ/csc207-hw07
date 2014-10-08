package Ushahidi;

/**
 * Methods for working with UshahidiIncidents.
 * public Methods are:
 * printIncident, which prints the relevant data for a Ushahidi inicident 
 * filterUshahidiIncidents, which prints all ushahidi incidents which satisfy
 * a given criterion
 * printSpecifiedIncidents, which prints all incidents which happen between 
 * two dates
 * vectorOfIncidents, which returns a vector consisting of all incidents which
 * happen between two dates
 * nearbyIncidents, which returns all incidents which happen near the specified
 * location to the 
 * 
 * 
 * 
 * @author Harriet Zucker and William Royle
 * @date 5 October, 2014
 */
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Vector;
import java.util.function.Predicate;

import edu.grinnell.glimmer.ushahidi.UshahidiIncident;
import edu.grinnell.glimmer.ushahidi.UshahidiIncidentList;

public class UshahidiExtensions
{
  //+---------+-----------------------------------------------------------
  //| Methods |
  //+---------+

  /**
   * Print an UshahidiIncident, displaying incident number, event date, location
   * status
   */
  public static void printIncident(PrintWriter pen, UshahidiIncident incident)
  {
    // print each specified piece of information.
    pen.println("Incident #: " + incident.getTitle());
    pen.println("Date: " + incident.getDate());
    pen.println(incident.getDescription().toString());
    pen.println("Location: " + incident.getLocation());
    pen.println("Status (MODE, ACTIVE, VERIFIED): " + incident.getMode()
                + " / " + incident.getActive() + " / " + incident.getVerified());
    // extra spacing
    pen.println("");
  }// PrintIncident

  /**
   * General way to filter a UshahidiIncidentList. 
   */
  public static void
    filterUshahidiIncidents(UshahidiIncidentList incidents,
                            Predicate<? super UshahidiIncident> pred,
                            PrintWriter pen)
      throws Exception
  {
    pen.println("The list of incidents which fullfil the criterion is:");
    boolean incidentsFound=false;
    while (incidents.hasMoreIncidents())
      {
        // event is next unseen incident
        UshahidiIncident event = incidents.nextIncident();
        if (pred.test(event))
          {
            // print specific incident
            printIncident(pen, event);
            incidentsFound=true;
          }// if
      }// while
    if (!incidentsFound)
      {
        pen.println("(Empty)");
      }
  }// filterUshahidiIncidents

  /**
   * Prints all incidents that happen between two specified dates.  
   */
  public static void printSpecifiedIncidents(UshahidiIncidentList incidents,
                                             LocalDateTime start,
                                             LocalDateTime end)
    throws Exception
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    pen.println("The list of incidents which fullfil the criterion is:");
    boolean incidentsFound=false;
    while (incidents.hasMoreIncidents())
      {
        // specific event is the next unseen incident
        UshahidiIncident event = incidents.nextIncident();
        // if event falls between specified dates
        if (event.getDate().isBefore(end) && event.getDate().isAfter(start))
          {
            // print specific incident
            printIncident(pen, event);
            incidentsFound=true;
          } // if
      } // while
    if (!incidentsFound)
      {
        pen.println("(Empty)");
      }
  }// printSpecifiedIncidents

  /**
   * Builds and returns a vector of all the incidents that fall between two specified dates.
   */
  public static Vector<UshahidiIncident>
    vectorOfIncidents(UshahidiIncidentList incidents, LocalDateTime start,
                      LocalDateTime end)
      throws Exception
  {
    // declare new vector to hold results
    Vector<UshahidiIncident> specifiedIncidents =
        new Vector<UshahidiIncident>();
    while (incidents.hasMoreIncidents())
      {
        // specific event is the next unseen incident
        UshahidiIncident event = incidents.nextIncident();
        // if event falls between specified dates
        if (event.getDate().isBefore(end) && event.getDate().isAfter(start))
          {
            // add the event to the new vector
            specifiedIncidents.add(event);
          }// if event falls between specified dates
      }// while
    return specifiedIncidents;
  }// vectorOfIncidents

  /**
   * Creates a new vector of all the incidents that fall within a given distance,
   * given in kilometers, of a given location. 
   */
  public static Vector<UshahidiIncident>
    nearbyIncidents(Vector<UshahidiIncident> incidents, double latitude,
                    double longitude, double distance)
  {
    // create new result vector
    Vector<UshahidiIncident> specifiedIncidents =
        new Vector<UshahidiIncident>();
    for (int count = 0; count < incidents.size(); count++)
      {
        UshahidiIncident event = incidents.get(count);
        //use distFrom to check distance
        if (distFrom((float) event.getLocation().getLatitude(),
                     (float) event.getLocation().getLongitude(),
                     (float) latitude, (float) longitude) <= distance)
          {
            // add event to vector
            specifiedIncidents.add(event);
          }// if
      }// for
    return specifiedIncidents;
  }// nearbyIncidents

  /**
   * Given two location in longitude and latitude, this method calculates the distance
   * between the two locations in km.
   * The distance formula was obtained from 
   * http://stackoverflow.com/questions/837872/
   * calculate-distance-in-meters-when-you-know-longitude-and-latitude-in-java
   * (Haversine method)
   * @pre: lat1, lat2 must be valid longitudes while lng1, lng2 must be valid longitudes
   */
  private static float distFrom(float lat1, float lng1, float lat2, float lng2)
  {
    double earthRadius = 6371; //kilometers
    double dLat = Math.toRadians(lat2 - lat1);
    double dLng = Math.toRadians(lng2 - lng1);
    double a =
        Math.sin(dLat / 2) * Math.sin(dLat / 2)
            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
            * Math.sin(dLng / 2) * Math.sin(dLng / 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    float dist = (float) (earthRadius * c);
    return dist;
  }// distFrom

}// UshahidiExtensions
