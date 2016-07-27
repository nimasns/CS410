package edu.pdx.cs410J.seyed;

import edu.pdx.cs410J.AbstractAppointmentBook;
import edu.pdx.cs410J.AppointmentBookParser;
import edu.pdx.cs410J.ParserException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

/**
 * Created by Nima on 7/8/16.
 * TextPareser class
 */
public class TextParser implements AppointmentBookParser{

    private String fileName;
    AppointmentBook appointmentBook = new AppointmentBook(null);


    public TextParser(String fileName) {
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
                if(parsedAppointment.length != 4) {
                    throw new ParserException("Format is invalid 1");
                }

                Appointment appointment = new Appointment(parsedAppointment[1], parsedAppointment[2], parsedAppointment[3]);
                appointmentBook.addAppointment(appointment);
                appointmentBook.setOwner(parsedAppointment[0]);
            }
        } catch (IOException e) {
            throw new ParserException("Format is invalid 2");
        } catch (ParseException e) {
            throw new ParserException("Format is invalid 3");
        }

        return appointmentBook;
    }
}
