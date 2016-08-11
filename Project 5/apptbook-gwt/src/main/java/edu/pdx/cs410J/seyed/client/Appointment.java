package edu.pdx.cs410J.seyed.client;

import edu.pdx.cs410J.AbstractAppointment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Appointment extends AbstractAppointment
{
    private String description;
    private Date beginDate;
    private Date endDate;
    private String beginTime;
    private String endTime;

    public Appointment(String description, Date beginDate, Date endDate){
        this.description = description;
        this.beginDate = beginDate;
        this.endDate = endDate;

    }

    public Appointment(String Description, String beginTime, String endTime) {
        this.description = Description;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public Appointment() {
    }

    @Override
    public String getBeginTimeString()
    {
        //return "START " + getBeginTime();
        return beginTime;
    }

    @Override
    public String getEndTimeString()
    {
        //return "END + " + getEndTime();
        return endTime;
    }

    @Override
    public Date getEndTime()
    {
        return endDate;
        //return new Date();
    }

    @Override
    public String getDescription()
    {
        return description;
        //return "My description";
    }

    @Override
    public Date getBeginTime()
    {
        return beginDate;
        //return new Date();
    }


    //add CompareTo
}
