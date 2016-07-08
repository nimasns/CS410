package edu.pdx.cs410J.seyed;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link Appointment} class.
 */
public class AppointmentTest {

  @Ignore
  @Test(expected = UnsupportedOperationException.class)
  public void getBeginTimeStringNeedsToBeImplemented() {
    Appointment appointment = new Appointment("", "", "");
    appointment.getBeginTimeString();
  }

  @Test
  public void initiallyAllAppointmentsHaveTheSameDescription() {
    Appointment appointment = new Appointment(null, null, null);
    assertThat(appointment.getDescription(), equalTo(null));
  }

  @Test
  public void forProject1ItIsOkayIfGetBeginTimeReturnsNull() {
    Appointment appointment = new Appointment("", "", "");
    assertThat(appointment.getBeginTime(), is(nullValue()));
  }

  @Test
  public void AppointmentWithDescription() {
    String description = "Description";
    Appointment appointment = new Appointment(description, "", "");
    assertThat(appointment.getDescription(), equalTo(description));
  }

  @Test
  public void AppointmentWithStartTime() {
    String beginTime = "5/2-2014 12:30";
    Appointment appointment = new Appointment("", beginTime, "");
    assertThat(appointment.getBeginTimeString(), containsString(beginTime));
  }

  @Test
  public void AppointmentWithEndtTime() {
    String endTime = "5/2/2014 12:30";
    Appointment appointment = new Appointment("", "", endTime);
    assertThat(appointment.getEndTimeString(), containsString(endTime));
  }

}
