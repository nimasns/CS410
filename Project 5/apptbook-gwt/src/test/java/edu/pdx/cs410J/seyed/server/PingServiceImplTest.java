package edu.pdx.cs410J.seyed.server;

import edu.pdx.cs410J.seyed.client.AppointmentBook;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PingServiceImplTest {

  @Test
  public void pingReturnsExpectedAirline() {
    PingServiceImpl service = new PingServiceImpl();
    AppointmentBook airline = service.ping();
    assertThat(airline.getAppointments().size(), equalTo(1));
  }
}
