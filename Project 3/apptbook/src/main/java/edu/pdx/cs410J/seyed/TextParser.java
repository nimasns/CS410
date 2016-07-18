package edu.pdx.cs410J.seyed;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookParser;
import edu.pdx.cs410J.ParserException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Nima on 7/8/16.
 * TextPareser class
 */
public class TextParser implements AppointmentBookParser{

    private String fileName;
    public AppointmentBook appointmentBook = new AppointmentBook(null);


    TextParser(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public AbstractAppointmentBook parse() throws ParserException {
        try {
            FileReader input = new FileReader(this.fileName);
            BufferedReader bufferedReader = new BufferedReader(input);
            String oneLine;

            while ((oneLine = bufferedReader.readLine()) != null) {
                String[] parsedAppointment = oneLine.split(";");
                appointmentBook.setOwner(parsedAppointment[0]);
                Appointment appointment = new Appointment(parsedAppointment[1], parsedAppointment[2], parsedAppointment[3]);
                appointmentBook.addAppointment(appointment);
            }
        } catch (FileNotFoundException e) {
            throw new ParserException("FIle not Found.");
        } catch (IOException e) {
            throw new ParserException("Invalid file.");
        }

        return appointmentBook;
    }
}
