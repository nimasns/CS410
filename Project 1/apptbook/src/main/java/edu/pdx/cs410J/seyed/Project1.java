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
    String timeFormat = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

    /*for (String arg : args) {
      System.out.println(arg);
    }*/

    /**
     * First look for the options command, if they do not apear in the begining treat everything
     * else as the inputting arguements.
     */
    //Counts the number of arguments for distingutionig commands from other info
    int a=0;
    for(int i=0; args[0].startsWith("-"); i++) {
      a++;
      if (args[i].equals("-README")) {
        printReadme();
      } else if (args[i].equals("-print")) {
        printFlag = true;
      } else {
        System.err.println("Invalid option command");
      }
    }

    //Storing infromation in the correct feilds
    for (String arg : args) {
      if (arg.contains("-README") && !readmeFlag) {
        readmeFlag = true;
      } else if (arg.contains("-print") && !printFlag) {
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

    if (!formatCheck(dateFormat, beginDate + " " + beginTime)) {
      System.out.println(beginDate);
      System.err.println("Invalid Begin Time Format!!!!!");
      System.exit(1);
    } else if (!formatCheck(dateFormat, endDate + " " + endTime)) {
      System.err.println("Invalid End Time Format!!!!!");
      System.exit(1);
    }

    Appointment appointment = new Appointment(description, beginDate + " " + beginTime, endDate + " " + endTime);
    System.out.println(appointment);
    System.out.println(Arrays.toString(args));

    //if (printFlag) System.out.println(appointment.toString());

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