package edu.pdx.cs410J.seyed;


import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookDumper;

import java.io.IOException;
import java.util.Collection;
import java.io.*;

/**
 * Created by Nima on 7/8/16.
 * Class to implement TextDumper to save the information of all appointments into
 * a textfile.
 */
public class TextDumper implements AppointmentBookDumper {

    private String fileName;

    public TextDumper(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void dump(AbstractAppointmentBook abstractAppointmentBook) throws IOException {
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(file, false);
        Collection<Appointment> appointments = abstractAppointmentBook.getAppointments();

        for (Appointment app : appointments) {
            String save = abstractAppointmentBook.getOwnerName() + ";" + app.getDescription() + ";" + app.getBeginTimeString() + ";" + app.getEndTimeString() + "\n";
            fileWriter.write(save);
        }
        fileWriter.close();
    }
}
