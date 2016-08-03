package edu.pdx.cs410J.seyed;

import edu.pdx.cs410J.AbstractAppointment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Comparator;

public class Appointment extends AbstractAppointment implements Comparable<Appointment> {
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

  @Override
  public int compareTo(Appointment o) {
    try {
      if (this.beginTime == null) {
        throw new NullPointerException("No stored Start Time and Date.");
      }
      if (this.endTime == null) {
        throw new NullPointerException("No stored end Time and Date.");
      }
      long difference = this.beginTime.getTime() - o.endTime.getTime();

      if (difference > 0) {
        return 1;
      }

      if (difference < 0) {
        return -1;
      }

      //they are equal
      if (difference == 0 ) {
        return this.description.compareTo(o.description);
      }

    } catch (NullPointerException e) {
      System.out.println("Found a Null Pointer in compareTo");
    }
    return 0;
  }

}
