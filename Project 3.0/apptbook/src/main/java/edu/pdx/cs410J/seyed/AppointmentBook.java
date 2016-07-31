package edu.pdx.cs410J.seyed;

import edu.pdx.cs410J.AbstractAppointment;
import edu.pdx.cs410J.AbstractAppointmentBook;

import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Nima on 7/4/16.
 *
 */

public class AppointmentBook extends AbstractAppointmentBook {
    private String owner = null;
    private Collection<Appointment> appointments = new ArrayList<>();

    public AppointmentBook(String owner) {
        this.owner = owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
    @Override
    public String getOwnerName() {
        return owner;
    }

    @Override
    public Collection getAppointments() {
        return appointments;
    }

    @Override
    public void addAppointment(AbstractAppointment newAppointment) {
        appointments.add((Appointment) newAppointment);
    }
}
