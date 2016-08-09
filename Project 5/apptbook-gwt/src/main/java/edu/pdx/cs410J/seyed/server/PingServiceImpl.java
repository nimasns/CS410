package edu.pdx.cs410J.seyed.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import edu.pdx.cs410J.seyed.client.Appointment;
import edu.pdx.cs410J.seyed.client.AppointmentBook;
import edu.pdx.cs410J.seyed.client.PingService;

/**
 * The server-side implementation of the division service
 */
public class PingServiceImpl extends RemoteServiceServlet implements PingService
{
  @Override
  public AppointmentBook ping() {
    AppointmentBook book = new AppointmentBook();
    book.addAppointment(new Appointment());
    return book;
  }

  @Override
  protected void doUnexpectedFailure(Throwable unhandled) {
    unhandled.printStackTrace(System.err);
    super.doUnexpectedFailure(unhandled);
  }

}
