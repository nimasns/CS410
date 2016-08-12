package edu.pdx.cs410J.seyed.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import edu.pdx.cs410J.AbstractAppointmentBook;

/**
 * The client-side interface to the ping service
 */
public interface PingServiceAsync {

  /**
   * Return the current date/time on the server
   */
  void ping(AsyncCallback<AppointmentBook> async);

  void ping(String owner, String description, String beginTime, String endTime, AsyncCallback<AppointmentBook> async);

  void pingSearch(String owner, String description, String beginTime, String endTime, AsyncCallback<AppointmentBook> async);
}
