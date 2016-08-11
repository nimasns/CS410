package edu.pdx.cs410J.seyed.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import edu.pdx.cs410J.seyed.client.Appointment;
import edu.pdx.cs410J.seyed.client.AppointmentBook;
import edu.pdx.cs410J.seyed.client.PingService;
import edu.pdx.cs410J.seyed.client.ThrowThis;


/**
 * The server-side implementation of the division service
 */
public class PingServiceImpl extends RemoteServiceServlet implements PingService
{
    AppointmentBook book = new AppointmentBook();

  @Override
  public AppointmentBook ping() {
    book.addAppointment(new Appointment());
    return book;
  }


    @Override
    public AppointmentBook ping(String owner, String description, String beginTime, String endTime){
        if(owner == null || "".equals(owner)) {
            throw new ThrowThis("Owner is blank");
        }

        if(book.getOwnerName() == null) {
            book = new AppointmentBook(owner);
            book.addAppointment(new Appointment(description, beginTime, endTime));
        } else if (book.getOwnerName().equals(owner)) {
            book.addAppointment(new Appointment(description, beginTime, endTime));
        } else {
            throw new ThrowThis("Owner does not exist");
        }
        return book;
    }


    @Override
    public AppointmentBook ping(String owner, String beginTime, String endTime) throws ThrowThis {
        if(owner == null || "".equals(owner)) {
            throw new ThrowThis("Owner is blank");
        } else if(book.getOwnerName().equals(owner)) {
            System.out.println("Found Owner");
        } else {
            throw new ThrowThis("owner does not match");
        }
        return book;
    }

    @Override
  protected void doUnexpectedFailure(Throwable unhandled) {
    unhandled.printStackTrace(System.err);
    super.doUnexpectedFailure(unhandled);
  }

}
