package edu.pdx.cs410J.seyed.client;

import edu.pdx.cs410J.AbstractAppointmentBook;

import java.util.ArrayList;
import java.util.Collection;

public class AppointmentBook extends AbstractAppointmentBook<Appointment>
{

    private String owner;
    private Collection<Appointment> appts = new ArrayList<>();

    public AppointmentBook(String owner){
        this.owner = owner;
    }

    public AppointmentBook() {
    }


    @Override
    public String getOwnerName()
    {
        return owner;
    }

    @Override
    public Collection<Appointment> getAppointments()
    {
        return this.appts;
    }

    @Override
    public void addAppointment( Appointment appt )
    {
        this.appts.add(appt);
    }
}
