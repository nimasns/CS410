package edu.pdx.cs410J.seyed;

import edu.pdx.cs410J.AbstractAppointment;
import sun.security.krb5.internal.crypto.Des;

import javax.lang.model.element.NestingKind;
import javax.swing.*;
import javax.swing.text.html.parser.Entity;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Appointment extends AbstractAppointment {
  private String description;
  private String BeginTimeString;
  private String EndTimeString;
  private Date BeginTime;
  private Date EndTime;

  public Appointment(String Description, Date BeginTime, Date EndTime){
    this.description = Description;
    //this.BeginTimeString = BeginTime;
    //this.EndTimeString = EndTime;
    this.BeginTime = BeginTime;
    this.EndTime = EndTime;

  }

  public Appointment(String Description, String BeginTimeString, String EndTimeString) {
    this.description = Description;
    this.BeginTimeString = BeginTimeString;
    this.EndTimeString = EndTimeString;
  }

  @Override
  public String getBeginTimeString() {
    return BeginTimeString;
    //throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getEndTimeString() {
    return EndTimeString;
    //throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getDescription() {
    return description;
    //return "This method is not implemented yet";
  }

  @Override
  public  Date getEndTime() {
    return EndTime;
  }

  @Override
  public Date getBeginTime() {
    return BeginTime;
  }
}
