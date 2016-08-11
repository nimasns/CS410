package edu.pdx.cs410J.seyed.client;

import com.google.common.annotations.VisibleForTesting;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import edu.pdx.cs410J.AbstractAppointment;
import edu.pdx.cs410J.AbstractAppointmentBook;

import com.google.gwt.regexp.shared.MatchResult;
import java.util.Collection;
/**
 * A basic GWT class that makes sure that we can send an appointment book back from the server
 */
public class AppointmentBookGwt implements EntryPoint {
  //private final Alerter alerter;

    private TextBox ownerField;
    private TextBox descriptionField;
    private TextBox beginTimeField;
    private TextBox endTimeField;

    private Label ownerLabel = new Label("Owner Name: ");
    private Label descriptionLabel = new Label("Description: ");
    private Label beginTimeLabel = new Label("Begin Time: ");
    private Label endTimeLabel = new Label("End Time: ");

    String dateFormat = "(0?[1-9]|[012][0-9]|3[01])/(0?[1-9]|[12][0-9])/(\\d{4}|\\d{2}) ([0]?[0-9]|1[0-2]):[0-5][0-9] (am|AM|pm|PM)";

    /* THIS NEEDS TO GO AWAY
    @VisibleForTesting
  Button button;


    public AppointmentBookGwt() {
    this(new Alerter() {
      @Override
      public void alert(String message) {
        Window.alert(message);
      }
    });
  }

  @VisibleForTesting
  AppointmentBookGwt(Alerter alerter) {
    this.alerter = alerter;

    addWidgets();
  }

  private void addWidgets() {
    button = new Button("Ping Server");
    button.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent clickEvent) {
          pingServer();
      }
    });
  }

    private void pingServer() {
        PingServiceAsync async = GWT.create(PingService.class);
        async.ping(new AsyncCallback<AppointmentBook>() {

          @Override
          public void onFailure(Throwable ex) {
            alerter.alert(ex.toString());
          }

          @Override
          public void onSuccess(AppointmentBook airline) {
            StringBuilder sb = new StringBuilder(airline.toString());
            Collection<Appointment> flights = airline.getAppointments();
            for (Appointment flight : flights) {
              sb.append(flight);
              sb.append("\n");
            }
            alerter.alert(sb.toString());
          }
        });
    }

    @Override
  public void onModuleLoad() {
    RootPanel rootPanel = RootPanel.get();
    rootPanel.add(button);
  }*/

    @Override
    public void onModuleLoad() {
        ownerField = new TextBox();
        ownerField.setMaxLength(20);
        descriptionField = new TextBox();
        descriptionField.setMaxLength(50);
        beginTimeField = new TextBox();
        beginTimeField.setMaxLength(20);
        endTimeField = new TextBox();
        endTimeField.setMaxLength(20);


        //Add Appointment button
        Button buttonAdd = new Button("Add appointment");
        buttonAdd.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                String owner = ownerField.getText();
                String description = descriptionField.getText();
                String beginTime = beginTimeField.getText();
                String endTime = endTimeField.getText();
                String missing;

                if (owner == "") {
                    missing = "Owner is missing";
                    Window.alert(missing);
                } else if ((!formatCheck(dateFormat, beginTime) || (!formatCheck(dateFormat, endTime)))) {
                    if (!formatCheck(dateFormat, beginTime)) {
                        missing = "Incorrect Begin Time!!";
                    } else {
                        missing = "Incorrect End Time";
                    }
                    Window.alert(missing);
                } else {
                    PingServiceAsync async = GWT.create(PingService.class);
                    async.ping(owner, description, beginTime, endTime, new AsyncCallback<AbstractAppointmentBook>() {

                        @Override
                        public void onFailure(Throwable ex) {
                            if (ex instanceof Throwable) {
                                Window.alert(ex.toString());
                            } else {
                                Window.alert("onfailure: " + ex.toString());
                            }
                        }

                        public void onSuccess(AbstractAppointmentBook appointmentBook) {
                            StringBuilder build = new StringBuilder(appointmentBook.toString());
                            Collection<AbstractAppointment> appointments = appointmentBook.getAppointments();
                            for (AbstractAppointment app : appointments) {
                                build.append("\n Appointment: ");
                                build.append(app);
                            }
                            Window.alert(build.toString());
                        }
                    });
                }
            }
        });



        //UI
        VerticalPanel owneradd = new VerticalPanel();
        owneradd.getElement().getStyle().setPadding(20, Style.Unit.PX);

        //Adding new appointment
        owneradd.add(ownerLabel);
        owneradd.add(ownerField);
        owneradd.add(descriptionLabel);
        owneradd.add(descriptionField);
        owneradd.add(beginTimeLabel);
        owneradd.add(beginTimeField);
        owneradd.add(endTimeLabel);
        owneradd.add(endTimeField);


        TabPanel tp = new TabPanel();
        tp.add(owneradd, "Add appointment");
        tp.add(new HTML(readMe()), "Help");
        tp.selectTab(0);

        RootPanel.get().add(tp);

    }

    private static String readMe() {
        StringBuilder build = new StringBuilder();
        build.append("\n\n");
        build.append("Project 5 README");
        build.append("Seyed Nima Sajadpour");
        build.append("This program will deal with the appointment books and theirinfo");
        build.append("There are two command line commands which can be used");
        build.append("As well as the inputted information should be in the below format");
        build.append("---------------\n");
        build.append("usage: java edu.pdx.edu.cs410J.<login-id>.Project3 [options] <args>\n");
        build.append("args are (in this order):");
        build.append("owner             - The person whose owns the appt book");
        build.append("description       - A description of the appointment");
        build.append("beginTime         - When the appt begins (12-hour time)");
        build.append("endTime           - When the appt ends (12-hour time)");
        build.append("-------------\n");
        build.append("Options are (options may appear in any order");
        build.append("Date and time should be in the format: mm/dd/yyyy hh:mm \n");

        String readme = build.toString();
        return readme;
    }


    /**
     * Check the format of the entered time
     * @param dateFormat
     * @param time
     * @return
     */
    private boolean formatCheck(String dateFormat, String time) {
        RegExp regex = RegExp.compile(dateFormat);
        MatchResult match = regex.exec(time);
        return match != null && match.getGroup(0).equals(time);
    }
    }
