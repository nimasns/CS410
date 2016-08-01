package edu.pdx.cs410J.seyed;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Nima on 7/19/16.
 * Prerry Printer Class
 */
public class PrettyPrinter implements AppointmentBookDumper {

    private String filename = null;

    public PrettyPrinter(String filename){
        this.filename = filename;
    }

    @Override
    public void dump(AbstractAppointmentBook abstractAppointmentBook) throws IOException {
        File file = new File(filename);
        FileWriter aFWriter = new FileWriter(file, false); //false to overwrite

        Collection<Appointment> appointments = abstractAppointmentBook.getAppointments();

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm");
        Date beginDate = null;
        Date endDate = null;

        aFWriter.write("--- Appointment Book ---\n\n Owner Name: ");
        aFWriter.write(abstractAppointmentBook.getOwnerName());
        aFWriter.write("\n\n-------------------------------------------\nAppointments:\n");
        for (Appointment app : appointments) {
            try {
                beginDate = format.parse(app.getBeginTimeString());
            } catch (ParseException e) {
                System.out.println("Date format incorrect");
            }
            try {
                endDate = format.parse(app.getEndTimeString());
            } catch (ParseException e) {
                System.out.println("Date format incorrect");
            }
            int duration = (int) ((endDate.getTime() - beginDate.getTime())
                    / (1000*60));

            String aline = "Description: " + app.getDescription() + " Start Time: " + app.getBeginTimeString()
                    + " End Time: " + app.getEndTimeString() + " Duration: " + duration + " minutes\n";
            aFWriter.write(aline);
        }
        aFWriter.write("\n--- End Appointment ---\n");
        aFWriter.close();
    }

    public void screendump(AbstractAppointmentBook abstractAppointmentBook) {
        Collection<Appointment> appointments = abstractAppointmentBook.getAppointments();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm");
        Date beginTime = null;
        Date endTime = null;

        System.out.print("******* Appointment Book *******\n\nOwner Name: ");
        System.out.print(abstractAppointmentBook.getOwnerName());
        System.out.print("\n\n------------------------------\nAppointments:\n");
        for (Appointment app : appointments) {
            System.out.print("hi " + app.getBeginTimeString());
            try {
                beginTime = format.parse(app.getBeginTimeString());
                System.out.print(app.getBeginTimeString());
            } catch (ParseException e) {
                System.out.println("Date format incorrect");
            }
            try {
                endTime = format.parse(app.getEndTimeString());
            } catch (ParseException e) {
                System.out.println("Date format incorrect");
            }
            int duration = (int) ((endTime.getTime() - beginTime.getTime())
                    / (1000*60));
            String aline = "From: " + app.getDescription() + " Start Time: " + app.getBeginTimeString()
                    + " End Time: " + app.getEndTimeString() + " Duration: " + duration + " minutes\n";
            System.out.print(aline);
        }
        System.out.print("\n--- End Appointment ---\n");
    }
}
