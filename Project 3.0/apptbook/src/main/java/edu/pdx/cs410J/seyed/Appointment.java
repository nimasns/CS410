package edu.pdx.cs410J.seyed;

import edu.pdx.cs410J.AbstractAppointment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Appointment extends AbstractAppointment {
  private String description;
  private Date beginTime;
  private Date endTime;

  public Appointment(String description, Date beginTime, Date endTime){
    this.description = description;
    this.beginTime = beginTime;
    this.endTime = endTime;

  }

  public Appointment(String Description, String beginTime, String endTime) throws ParseException {
    this.description = Description;

    SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyy hh:mm a");
    this.beginTime = df.parse(beginTime.trim());
    this.endTime = df.parse(endTime.trim());
  }

  @Override
  public String getBeginTimeString() {
    SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyy hh:mm a");
    return df.format(beginTime);
    //return BeginTimeString;
    //throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getEndTimeString() {
    SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyy hh:mm a");
    return df.format(endTime);
    //return EndTimeString;
    //throw new UnsupportedOperationException("This method is not implemented yet");
  }

  @Override
  public String getDescription() {
    return description;
    //return "This method is not implemented yet";
  }

  @Override
  public  Date getEndTime() {
    return endTime;
  }

  @Override
  public Date getBeginTime() {
    return beginTime;
  }
}
