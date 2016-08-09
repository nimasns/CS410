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
    public void dump(AbstractAppointmentBook appointment) throws IOException {
        File file = new File(filename);
        FileWriter aFWriter = new FileWriter(file, false); //false to overwrite

        //Collection<Appointment> appointments = abstractAppointmentBook.getAppointments();
        Collection<Appointment> appointments = appointment.getAppointments();

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm");
        Date beginDate = null;
        Date endDate = null;

        aFWriter.write("--- Appointment Book ---\n\n Owner Name: ");
        aFWriter.write(appointment.getOwnerName());
        aFWriter.write("\n-------------------------------------------\nAppointments:\n");
        for (Appointment each : appointments) {
            System.out.println(each.getDescription());
            try {
                beginDate = format.parse(each.getBeginTimeString());
                System.out.println(beginDate);
            } catch (ParseException e) {
                System.out.println("Date format incorrect");
            }
            try {
                endDate = format.parse(each.getEndTimeString());
            } catch (ParseException e) {
                System.out.println("Date format incorrect");
            }
            int duration = (int) ((endDate.getTime() - beginDate.getTime())
                    / (1000*60));

            String aline = "Description: " + each.getDescription() + " Start Time: " + each.getBeginTimeString()
                    + " End Time: " + each.getEndTimeString() + " Duration: " + duration + " minutes\n";
            aFWriter.write(aline);
        }
        aFWriter.write("\n--- End Appointment ---\n");
        aFWriter.close();
    }

    public void screendump(AbstractAppointmentBook appointment) {
        Collection<Appointment> appointments = appointment.getAppointments();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm");
        Date beginTime = null;
        Date endTime = null;

        System.out.print("******* Appointment Book *******\n\nOwner Name: ");
        System.out.print(appointment.getOwnerName());
        System.out.print("\n\n------------------------------\nAppointments:\n");
        System.out.print(appointment.toString());
        for (Appointment each : appointments) {
            System.out.print("hi " + each.getBeginTimeString());
            try {
                beginTime = format.parse(each.getBeginTimeString());
                System.out.print(each.getBeginTimeString());
            } catch (ParseException e) {
                System.out.println("Date format incorrect");
            }
            try {
                endTime = format.parse(each.getEndTimeString());
            } catch (ParseException e) {
                System.out.println("Date format incorrect");
            }
            int duration = (int) ((endTime.getTime() - beginTime.getTime())
                    / (1000*60));
            String aline = "From: " + each.getDescription() + " Start Time: " + each.getBeginTimeString()
                    + " End Time: " + each.getEndTimeString() + " Duration: " + duration + " minutes\n";
            System.out.print(aline);
        }
        System.out.print("\n--- End Appointment ---\n");
    }
}
