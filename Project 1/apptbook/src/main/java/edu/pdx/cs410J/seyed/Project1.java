package edu.pdx.cs410J.seyed;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookDumper;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The main class for the CS410J appointment book Project
 */
public class Project1 {

  public static void main(String[] args) {
    Class c = AbstractAppointmentBook.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath

    if (args.length == 0) {
      System.err.println("Missing command line arguments");
    }

    //local var for storing the info
    String owner = null;
    String description = null;
    boolean printFlag = false;
    boolean readmeFlag = false;
    String beginDate = null;
    String beginTime = null;
    String endDate = null;
    String endTime = null;

    String dateFormat = "(0?[1-9]|[012][0-9]|3[01])/(0?[1-9]|[12][0-9])/(\\d{4}|\\d{2}) ([01]?[0-9]|2[0-3]):[0-5][0-9]";

    //Storing infromation in the correct feilds
    for (String arg : args) {
      if (arg.startsWith("-README") && !readmeFlag) {
        readmeFlag = true;
        printReadme();
      } else if (arg.startsWith("-print") && !printFlag) {
        printFlag = true;
      } else if (owner == null) {
        owner = arg;
      } else if (description == null) {
        description = arg;
      } else if (beginDate == null) {
        beginDate = arg;
      } else if (beginTime == null) {
        beginTime = arg;
      } else if (endDate == null) {
        endDate = arg;
      } else if (endTime == null) {
        endTime = arg;
      }
    }

    //missing fields check
    if (description == null) {
      System.err.println("Missing description field!");
    } else if (owner == null) {
      System.err.println("Missing owner field!");
    } else if (beginDate == null) {
      System.err.println("Missing begin date field!");
    } else if (beginTime == null) {
      System.err.println("Missing begin time field!");
    } else if (endDate == null) {
      System.err.println("Missing end date field!");
    } else if (endTime == null) {
      System.err.println("Missing end time field!");
    } else if (!formatCheck(dateFormat, beginDate + " " + beginTime)) {
      System.err.println("Invalid begin time format!");
    } else if (!formatCheck(dateFormat, endDate + " " + endTime)) {
      System.err.println("Invalid end time format!");
    }

    Appointment appointment = new Appointment(description, beginDate + " " + beginTime, endDate + " " + endTime);
    AppointmentBook appointmentBook = new AppointmentBook(owner, appointment);

    if (printFlag) {
      System.out.println(appointmentBook.toString());
      System.out.println(appointment.toString());
    }
    System.exit(1);
  }

  private static boolean formatCheck(String format, String time) {
    Pattern pattern = Pattern.compile(format);
    Matcher matcher = pattern.matcher(time);
    boolean matches = matcher.matches();
    return matches;
  }
  private static void printReadme() {
    System.out.println("\n\n");
    System.out.println("Project 1 README");
    System.out.println("Seyed Nima Sajadpour");
    System.out.println("This program will deal with the appointment books and theirinfo");
    System.out.println("There are two command line commands which can be used");
    System.out.println("As well as the inputted information should be in the below format");
    System.out.println("---------------\n");
    System.out.println("usage: java edu.pdx.edu.cs410J.<login-id>.Project1 [options] <args>\n");
    System.out.println("args are (in this order):");
    System.out.println("owner             - The person whose owns the appt book");
    System.out.println("description       - A description of the appointment");
    System.out.println("beginTime         - When the appt begins (24-hour time)");
    System.out.println("endTime           - When the appt ends (24-hour time)");
    System.out.println("-------------\n");
    System.out.println("Options are (options may appear in any order");
    System.out.println("-print            - Prints a description of the new appointment");
    System.out.println("-README           - Prints a README for this project and exits");
    System.out.println("Date and time should be in the format: mm/dd/yyyy hh:mm \n");
    System.exit(2);
  }

}