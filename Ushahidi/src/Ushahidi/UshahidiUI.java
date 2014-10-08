package Ushahidi;

/**
 * Ushahidi user interface that filters specified incidents from a UshahidiWebClient.
 * Allows user to filter by date, title and description
 * 
 * @author Harriet Zucker and William Royle
 * @date 5 October, 2014
 */
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Predicate;
import edu.grinnell.glimmer.ushahidi.UshahidiIncident;
import edu.grinnell.glimmer.ushahidi.UshahidiIncidentList;
import edu.grinnell.glimmer.ushahidi.UshahidiWebClient;

public class UshahidiUI
{
  public static void main(String[] args)
    throws Exception
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner user_input = new Scanner(System.in);
    String input;
    String server;
    // enter name of server you wish to access
    pen.println("Please enter the server URL of UshahidiIncidents:");
    boolean validURLFound = false;
    UshahidiWebClient webClient;
    Set<UshahidiIncident> mySet = new HashSet<UshahidiIncident>();
    // We now get the server URL, testing user input
    while (!validURLFound)
      {
        validURLFound = true;
        try
          {
            server = user_input.nextLine();
            webClient = new UshahidiWebClient(server);
            mySet =
                new HashSet<UshahidiIncident>(
                                              Arrays.asList(webClient.getIncidents()));
          }// try
        catch (Exception E)
          {
            pen.println("Please enter a valid server URL:");
            validURLFound = false;
          }// catch
      }// try

    //make new UshahidiIncidentList from the webClient
    UshahidiIncidentList incidents = new UshahidiIncidentList(mySet);
    // Loop verifies that user chooses between quit, date, title, description
    boolean complete = false;
    while (!complete)
      {
        pen.println("Please type criteria you would like to filter by, or type 'quit' to exit.");
        pen.println("     Options are: date, title, and description.");
        input = user_input.nextLine();
        // If the user gives quit as input, we terminate the program
        if (input.equalsIgnoreCase("quit"))
          {
            // quit program and closer user_input
            complete = true;
            pen.println("Program Completion");
            user_input.close();
            return;

          }// if
        else if (input.equalsIgnoreCase("date"))
          {
            complete = true;
            // format date correctly
            DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            // We get user input, and ensure it is valid
            boolean valid = false;
            String dateStr = "";
            while (!valid)
              {
                valid = true;
                try
                  {
                    pen.println("Please enter date you would like to filter in form 'yyyy-MM-dd HH:mm':");
                    dateStr = user_input.nextLine();
                    LocalDateTime.parse(dateStr, formatter);
                  }
                catch (Exception E)
                  {
                    valid = false;
                  }
              }

            LocalDateTime date = LocalDateTime.parse(dateStr, formatter);
            //Predicate to check if date is the same as incident.getDate()
            Predicate<UshahidiIncident> dateMatch = (incident) ->
              {
                if (incident.getDate().equals(date))
                  return true;
                else
                  {
                    return false;
                  }
              };
            //check using filter procedure
            UshahidiExtensions.filterUshahidiIncidents(incidents, dateMatch,
                                                       pen);
          }// else if
        else if (input.equalsIgnoreCase("title"))
          {
            complete = true;
            pen.println("Please enter title you would like to filter:");
            String title = user_input.nextLine().toString();
            //Predicate to check if title is the same as incident.getTitle()
            Predicate<UshahidiIncident> titleMatch = (incident) ->
              {
                if (incident.getTitle().equalsIgnoreCase(title))
                  return true;
                else
                  return false;
              };
            //check using filter procedure
            UshahidiExtensions.filterUshahidiIncidents(incidents, titleMatch,
                                                       pen);
          }//else if input is title
        else if (input.equalsIgnoreCase("description"))
          {
            complete = true;
            pen.println("Please enter description you would like to filter:");
            String description = user_input.nextLine().toString();
            //Predicate to check if description is the same as incident.getDescription()
            Predicate<UshahidiIncident> descriptionMatch = (incident) ->
              {
                if (incident.getDescription().equalsIgnoreCase(description))
                  return true;
                else
                  return false;

              };
            //check using filter procedure
            UshahidiExtensions.filterUshahidiIncidents(incidents,
                                                       descriptionMatch, pen);
          }// else if
      }// while
    pen.println("Program Completion");
    user_input.close();
  }// main
}// UshahidiUI
