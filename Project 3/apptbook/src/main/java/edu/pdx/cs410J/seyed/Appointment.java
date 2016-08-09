package edu.pdx.cs410J.seyed;

import edu.pdx.cs410J.AbstractAppointment;
import sun.security.krb5.internal.crypto.Des;

import javax.lang.model.element.NestingKind;
import javax.swing.*;
import javax.swing.text.html.parser.Entity;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Appointment extends AbstractAppointment {
  private String description;
  private Date BeginTime;
  private Date EndTime;

  public Appointment(String Description, Date BeginTime, Date EndTime){
    this.description = Description;
    this.BeginTime = BeginTime;
    this.EndTime = EndTime;

  }
/*
  public Appointment(String Description, String BeginTimeString, String EndTimeString) {
    this.description = Description;
    this.BeginTimeString = BeginTimeString;
    this.EndTimeString = EndTimeString;
  }
*/

  public Appointment(String Description, String BeginTime, String EndTime) throws ParseException {
    this.description = Description;

    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm");
    this.BeginTime = format.parse(BeginTime.trim());
    this.EndTime = format.parse(EndTime.trim());
  }

  @Override
  public String getDescription() {
    return description;
    //return "This method is not implemented yet";
  }

  @Override
  public String getBeginTimeString() {
    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm");
    return format.format(BeginTime);
    //return BeginTimeString;
    //throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getEndTimeString() {
    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm");
    return format.format(EndTime);
    //return EndTimeString;
    //throw new UnsupportedOperationException("This method is not implemented yet");
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
