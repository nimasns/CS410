package edu.pdx.cs410J.seyed;

import edu.pdx.cs410J.web.HttpRequestHelper;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The main class that parses the command line and communicates with the
 * Appointment Book server using REST.
 */
public class Project4 {

    public static final String MISSING_ARGS = "Missing command line arguments";

    public static void main(String... args) throws ParseException {
        String hostName = null;
        String portString = null;
        String key = null;
        String value = null;

        String owner = null;
        String description = null;
        String beginDate = null;
        String beginTime = null;
        String begin = null;
        String endDate = null;
        String endTime = null;
        String end = null;
        Date beginDateTime;
        Date endDateTime;
        String stringBegin = null;
        String stringEnd = null;


        boolean printFlag = false;
        boolean readmeFlag = false;
        boolean searchFlag = false;
        boolean portFlag = false;
        boolean hostFlag = false;
        boolean serverFlag = true;

        String dateFormat = "(0?[1-9]|[012][0-9]|3[01])/(0?[1-9]|[12][0-9])/(\\d{4}|\\d{2}) ([0]?[0-9]|1[0-2]):[0-5][0-9] (am|AM|pm|PM)";
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm a");

        for (String arg : args) {
            if (arg.startsWith("-README") && !readmeFlag) {
                readmeFlag = true;
                printReadme();

            }else if (arg.startsWith("-search")) {
                searchFlag = true;

            } else if (arg.startsWith("-print") && !printFlag) {
                printFlag = true;

            } else if (arg.startsWith("-host") && !hostFlag) {
                hostFlag = true;

            } else if (hostFlag && hostName == null) {
                hostName = arg;

            } else if(arg.startsWith("-port") && !portFlag) {
                portFlag = true;

            } else if (portFlag && portString == null) {
                portString = arg;

            } else if (owner == null) {
                owner = arg;

            } else if (description == null) {
                description = arg;

            } else if (beginDate == null) {
                beginDate = arg;

            } else if (beginTime == null) {
                beginTime = arg;

            } else if (begin == null) {
                begin = arg;

            } else if (endDate == null) {
                endDate = arg;

            } else if (endTime == null) {
                endTime = arg;

            } else if (end == null) {
                end = arg;
            } else {
                usage("Extraneous command line argument: " + arg);
            }
        }

        if (hostName == null) {
            usage( MISSING_ARGS );

        } else if ( portString == null) {
            usage( "Missing port" );

        } else if (owner == null) {
            errorMessage("Missing owner field!");

        } else if (description == null) {
            errorMessage("Missing description field!");

        } else if (beginDate == null) {
            errorMessage("Missing begin date field!");

        } else if (beginTime == null) {
            errorMessage("Missing begin time field!");

        } else if (begin == null) {
            errorMessage("Missing begin time of day");

        } else if (endDate == null) {
            errorMessage("Missing end date field!");

        } else if (endTime == null) {
            errorMessage("Missing end time field!");

        } else if (end == null) {
            errorMessage("Missing end time of day");

        } else if (!formatCheck(dateFormat, beginDate + " " + beginTime + " " + begin)) {
            errorMessage("Invalid begin time format!");

        } else if (!formatCheck(dateFormat, endDate + " " + endTime + " " + end)) {
            errorMessage("Invalid end time format!");
        }


        beginDateTime = format.parse(beginDate + " " + beginTime + " " + begin);
        endDateTime = format.parse(endDate + " " + endTime + " " + end);

        stringBegin = format.format(beginDateTime);
        stringEnd = format.format(endDateTime);

        AppointmentBook aAppointmentBook = new AppointmentBook(owner);
        Appointment aAppointment = new Appointment(description, beginDateTime, endDateTime);


        int port;
        try {
            port = Integer.parseInt( portString );
            
        } catch (NumberFormatException ex) {
            usage("Port \"" + portString + "\" must be an integer");
            return;
        }

        AppointmentBookRestClient client = new AppointmentBookRestClient(hostName, port);

        HttpRequestHelper.Response response = null;


        if (searchFlag) {
            try {
                response = client.searchTime(owner, description, stringBegin, stringEnd);
            } catch (IOException ex) {
                System.out.println("Web connection issue");
                serverFlag = false;
            }
        } else {
            try {
                response = client.addAppointment(owner, description, stringBegin, stringEnd);
            } catch (IOException ex) {
                System.out.println("Web connection issue");
                serverFlag = false;
            }
        }

        if(serverFlag) {
            checkResponseCode(HttpURLConnection.HTTP_OK, response);
            System.out.println(response.getContent());
        }


        if (printFlag) {
            System.out.println(aAppointmentBook.toString());
            System.out.println(aAppointment.toString());
        }

        System.exit(0);
    }

    /**
     * Makes sure that the give response has the expected HTTP status code
     * @param code The expected status code
     * @param response The response from the server
     */
    private static void checkResponseCode( int code, HttpRequestHelper.Response response )
    {
        if (response.getCode() != code) {
            error(String.format("Expected HTTP code %d, got code %d.\n\n%s", code,
                                response.getCode(), response.getContent()));
        }
    }

    private static void error( String message )
    {
        PrintStream err = System.err;
        err.println("** " + message);

        System.exit(1);
    }

    /**
     * Prints usage information for this program and exits
     * @param message An error message to print
     */
    private static void usage( String message )
    {
        PrintStream err = System.err;
        err.println("** " + message);
        err.println();
        err.println("usage: java Project4 host port [owner] [descriptio] [Begin Time] [End Time]");
        err.println("  host    Host of web server");
        err.println("  port    Port of web server");
        err.println("  owner             - The person whose owns the appt book");
        err.println("  description       - A description of the appointment");
        err.println("  beginTime         - When the appt begins (12-hour time)");
        err.println("  endTime           - When the appt ends (12-hour time)");
        err.println();
        err.println("Options are (options may appear in any order");
        err.println("-host hostname      - Host computer on which the server runs");
        err.println("-port port          - Port on which the server is listening");
        err.println("-search             - Appointments should be searched for");
        err.println("-print              - Prints a description of the new appointment");
        err.println("-README             - Prints a README for this project and exits");
        err.println();
        err.println("Date and time should be in the format: mm/dd/yyyy hh:mm \n");
        err.println("This simple program posts appointments to the server");
        err.println("If no value is specified, then all appointments are printed");
        err.println("If no key is specified, all appointments pairs are printed");
        err.println();

        System.exit(1);
    }

    /**
     * Print the Readme inofmration
     */
    private static void printReadme() {
        System.out.println("\n\n");
        System.out.println("Project 1 README");
        System.out.println("Seyed Nima Sajadpour");
        System.out.println("This program will deal with the appointment books and theirinfo");
        System.out.println("There are two command line commands which can be used");
        System.out.println("As well as the inputted information should be in the below format");
        System.out.println("---------------\n");
        System.out.println("usage: java edu.pdx.edu.cs410J.<login-id>.Project3 [options] <args>\n");
        System.out.println("args are (in this order):");
        System.out.println("owner             - The person whose owns the appt book");
        System.out.println("description       - A description of the appointment");
        System.out.println("beginTime         - When the appt begins (12-hour time)");
        System.out.println("endTime           - When the appt ends (12-hour time)");
        System.out.println("-------------\n");
        System.out.println("Options are (options may appear in any order");
        System.out.println("-pretty File       - Pretty print the appointment book to");
        System.out.println("                     a text file or standard out (file -)");
        System.out.println("-textFile file     - Where to read/write the appointment book");
        System.out.println("-print             - Prints a description of the new appointment");
        System.out.println("-README            - Prints a README for this project and exits");
        System.out.println("Date and time should be in the format: mm/dd/yyyy hh:mm \n");
        System.exit(2);
    }

    /**
     * Print a error message if something is missing
     * @param error
     */
    private static void errorMessage(String error) {
        System.out.println(error);
        System.exit(3);
    }

    /**
     * Check the format of the entered time
     * @param format
     * @param time
     * @return
     */
    private static boolean formatCheck(String format, String time) {
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(time);
        return matcher.matches();
    }
}