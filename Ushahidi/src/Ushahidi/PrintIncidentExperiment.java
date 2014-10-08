package Ushahidi;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Vector;

import edu.grinnell.glimmer.ushahidi.UshahidiCategory;
import edu.grinnell.glimmer.ushahidi.UshahidiIncident;
//import edu.grinnell.glimmer.ushahidi.UshahidiIncidentList;
import edu.grinnell.glimmer.ushahidi.UshahidiLocation;
//import edu.grinnell.glimmer.ushahidi.UshahidiClient;
//import edu.grinnell.glimmer.ushahidi.UshahidiWebClient;

public class PrintIncidentExperiment
{
  public static void main(String[] args)
    throws Exception
  {
    // Create the output device
    PrintWriter pen = new PrintWriter(System.out, true);

    /* // A few basic incidents
     UshahidiExtensions.printIncident(pen, UshahidiUtils.SAMPLE_INCIDENT);
     UshahidiExtensions.printIncident(pen, UshahidiUtils.randomIncident());
     UshahidiExtensions.printIncident(pen, UshahidiUtils.randomIncident());

     //credit to http://stackoverflow.com/questions/22463062/how-to-parse-format-dates-with-localdatetime-java-8
     //for showing how to change LocalDatetime
     String str = "2014-10-05 11:19";
     DateTimeFormatter formatter =
         DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
     LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
     UshahidiCategory[] categories =
         { new UshahidiCategory(1, "homework"),
          new UshahidiCategory(1, "boring") };
     // A newly created incident
     UshahidiIncident myIncident = new UshahidiIncident(1, "Working on HW7", dateTime,
                          new UshahidiLocation(1, "3rd floor Noyce", 11.4, 10.3),
                          "Trying to finish this assignment.", categories);
         UshahidiExtensions.printIncident(pen, myIncident);

     // One from a list
     UshahidiClient client = UshahidiUtils.SAMPLE_CLIENT;
     UshahidiExtensions.printIncident(pen, client.nextIncident());

     //One that requires connecting to the server
     UshahidiClient webclient = new UshahidiWebClient("http://ushahidi1.grinnell.edu/sandbox/");
     UshahidiExtensions.printIncident(pen, webclient.nextIncident());
     */
    String str = "2014-10-05 11:19";
    DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
    /*
        String str1 = "2014-10-04 11:19";
        DateTimeFormatter formatter1 =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime1 = LocalDateTime.parse(str1, formatter1);

        String str2 = "2014-10-06 11:19";
        DateTimeFormatter formatter2 =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime2 = LocalDateTime.parse(str2, formatter2);
    */
    String str3 = "2013-10-06 11:19";
    DateTimeFormatter formatter3 =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    LocalDateTime dateTime3 = LocalDateTime.parse(str3, formatter3);

    UshahidiCategory[] categories =
        { new UshahidiCategory(1, "homework"),
         new UshahidiCategory(1, "boring") };

    UshahidiCategory[] categories1 =
        { new UshahidiCategory(1, "homework"),
         new UshahidiCategory(1, "boring"), new UshahidiCategory(1, "sobored") };

    UshahidiCategory[] categories2 =
        { new UshahidiCategory(1, "homework"),
         new UshahidiCategory(1, "boring"), new UshahidiCategory(1, "sobored"),
         new UshahidiCategory(1, "dying") };

    ArrayList<UshahidiIncident> a1 = new ArrayList<UshahidiIncident>();
    a1.add(new UshahidiIncident(1, "Working on HW7", dateTime3,
                                new UshahidiLocation(1, "3rd floor Noyce",
                                                     11.4, 10.3),
                                "Trying to finish this assignment.", categories));
    a1.add(new UshahidiIncident(1, "Still working on HW7", dateTime,
                                new UshahidiLocation(1, "3rd floor Noyce",
                                                     11.4, 10.3),
                                "Still trying to finish this assignment.",
                                categories1));
    a1.add(new UshahidiIncident(1, "Working on HW7", dateTime,
                                new UshahidiLocation(1, "3rd floor Noyce",
                                                     19.99, 19.99),
                                "Dying to finish this assignment.", categories2));

    //UshahidiIncidentList incidents = new UshahidiIncidentList(a1);

    Vector<UshahidiIncident> a2 = new Vector<UshahidiIncident>();
    a2.addAll(a1);
    //Vector<UshahidiIncident> a3 =
    //UshahidiExtensions.vectorOfIncidents(incidents, dateTime1, dateTime2);

    //We test distance
    Vector<UshahidiIncident> a3 =
        UshahidiExtensions.nearbyIncidents(a2, 20.0, 20.0, 5);

    for (UshahidiIncident instance : a3)
      {
        UshahidiExtensions.printIncident(pen, instance);
      }
    //UshahidiExtensions.printSpecifiedIncidents(incidents, dateTime1, dateTime2);
  } // main(String[])
}