package edu.pdx.cs410J.seyed;

import edu.pdx.cs410J.AbstractAppointment;
import edu.pdx.cs410J.AbstractAppointmentBook;

import java.security.acl.Owner;
import java.util.Collection;

/**
 * Created by Nima on 7/4/16.
 */

public class AppointmentBook extends AbstractAppointmentBook {
    private String ownerName;
    private Appointment appointments;

    public AppointmentBook(String ownerName, Appointment appointments) {
        this.ownerName = ownerName;
        this.appointments = appointments;
    }
    @Override
    public String getOwnerName() {

        return ownerName;
    }

    @Override
    public Collection getAppointments() {

        return (Collection) appointments;
    }

    @Override
    public void addAppointment(AbstractAppointment abstractAppointment) {

    }
}
