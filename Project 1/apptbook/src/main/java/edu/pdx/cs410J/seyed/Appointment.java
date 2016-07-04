package edu.pdx.cs410J.seyed;

import edu.pdx.cs410J.AbstractAppointment;
import sun.security.krb5.internal.crypto.Des;

import javax.swing.text.html.parser.Entity;

public class Appointment extends AbstractAppointment {
  private String description;
  private String BeginTime;
  private String EndTime;

  public Appointment(String Description, String BeginTime, String EndTime){
    this.description = Description;
    this.BeginTime = BeginTime;
    this.EndTime = EndTime;
  }

  @Override
  public String getBeginTimeString() {
    return BeginTime;
    //throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getEndTimeString() {
    return EndTime;
    //throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getDescription() {
    return description;
    //return "This method is not implemented yet";
  }
}
